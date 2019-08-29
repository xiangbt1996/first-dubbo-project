package com.xiang.api.service.task;

import com.alibaba.dubbo.common.utils.StringUtils;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Map;

/**
 * 定时任务管理类
 */
public class QuartzManager {
    private static SchedulerFactory gSchedulerFactory = new StdSchedulerFactory();  //创建一个SchedulerFactory工厂实例
    private static String JOB_NAME = "BINGO_JOB_NAME";  					        //任务名组
    private static String JOB_GROUP_NAME = "XIANG_JOBGROUP_NAME";  					//任务组
    private static String TRIGGER_GROUP_NAME = "XIANG_TRIGGERGROUP_NAME";  			//触发器组
    private static String Cron_DIV = " ";

    /**
     * 判定指定名称的任务是否已存在
     * @param jobName
     * @return
     */
    public static boolean jobExists(String groupName, String jobName) throws Exception{
        JobKey jobKey = new JobKey(jobName, groupName);
        return gSchedulerFactory.getScheduler().checkExists(jobKey);
    }


    /**添加一个立即执行的任务，使用默认的任务组名，触发器名，触发器组名
     * @param jobName 任务名
     * @param cls 任务
     */
    public static void addNowJob(String jobName, Class<? extends Job> cls) {
        addNowJob(jobName, cls, null);
    }

    public static void addNowJob(String jobName, Class<? extends Job> cls, Map<String,Object> parameter) {
        addJob(jobName, cls, null, parameter);
    }


    /**添加一个定时任务，使用默认的任务组名，触发器名，触发器组名
     * @param jobName 任务名
     * @param cls 任务
     * @param time 时间设置，参考quartz说明文档
     */
    public static void addJob(String jobName, Class<? extends Job> cls, String time) {
        addJob(jobName, cls, time, null);
    }

    /**添加一个定时任务，使用默认的任务组名，触发器名，触发器组名  （带参数）
     * @param jobName 任务名
     * @param cls 任务
     * @param time 时间设置，参考quartz说明文档
     */
    public static void addJob(String jobName, Class<? extends Job> cls, String time, Map<String,Object> parameter) {
        addJob(jobName, null, null, cls, time, parameter);
    }

    /**添加一个定时任务
     * @param jobName	任务名
     * @param jobGroupName	任务组名
     * @param triggerGroupName	触发器组名
     * @param jobClass	任务
     * @param time	时间设置，参考quartz说明文档
     */
    public static void addJob(String jobName, String jobGroupName, String triggerGroupName,
                              Class<? extends Job> jobClass,
                              String time) {
        addJob(jobName, jobGroupName, triggerGroupName, jobClass, time, null);
    }

    /**添加一个定时任务  （带参数）
     * @param jobName	任务名
     * @param jobGroupName	任务组名
     * @param triggerGroupName	触发器组名
     * @param jobClass	任务
     * @param time	时间设置，参考quartz说明文档
     */
    public static void addJob(String jobName, String jobGroupName, String triggerGroupName,
                              Class<? extends Job> jobClass,
                              String time,
                              Map<String,Object> parameter) {
        try {
            if(StringUtils.isEmpty(jobName)){
                jobName = JOB_NAME;
            }
            if(StringUtils.isEmpty(jobGroupName)){
                jobGroupName = JOB_GROUP_NAME;
            }
            if(StringUtils.isEmpty(triggerGroupName)){
                triggerGroupName = TRIGGER_GROUP_NAME;
            }
            if(jobClass == null){
                throw new Exception("JOB Class Error = " + jobClass.getName());
            }

            if(jobExists(jobGroupName, jobName)){
                throw new Exception("JOB Name Exist = " + jobGroupName + "|" + jobName);
            }
            //触发器名与任务名称相一致
            String triggerName = jobName;
            Trigger trigger = null;

            Scheduler sched = gSchedulerFactory.getScheduler();
            JobDetail jobDetail= JobBuilder.newJob(jobClass).withIdentity(jobName,jobGroupName).build();// 任务名，任务组，任务执行类

            if(parameter != null && parameter.size() > 0) {
                for (String key : parameter.keySet()) {
                    jobDetail.getJobDataMap().put(key, parameter.get(key));                                //传参数
                }
            }

            if(StringUtils.isEmpty(time)){
                //立即执行的任务
                trigger = (SimpleTrigger) TriggerBuilder
                        .newTrigger()	 																	//创建一个新的TriggerBuilder来规范一个触发器
                        .withIdentity(triggerName, triggerGroupName)											//给触发器起一个名字和组名
                        .startNow()
                        .build();
            } else {
                //缓期定时执行的任务
                trigger = (CronTrigger) TriggerBuilder     // 触发器
                        .newTrigger()
                        .withIdentity(triggerName, triggerGroupName)
                        .withSchedule(CronScheduleBuilder.cronSchedule(time))
                        .build();

            }
            sched.scheduleJob(jobDetail, trigger);
            if (!sched.isShutdown()) {
                sched.start();      // 启动
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /** 修改一个任务的触发时间(使用默认的任务组名，触发器名，触发器组名)
     * @param jobName	任务名
     * @param time	新的时间设置
     */
    public static void modifyJob(String jobName, String time) {
        modifyJob(jobName, time, null, null);
    }

    public static void modifyJob(String jobName, String time, Class<? extends Job> jobClass, Map<String,Object> parameter) {
        modifyJob(jobName,null, null, jobClass, time, parameter);
    }

    /**
     * 修正一个当前执行任务配置
     * @param jobName
     * @param triggerGroupName
     * @param jobClass
     * @param time
     * @param parameter
     */
    public static void modifyJob(String jobName, String jobGroupName, String triggerGroupName, Class<? extends Job> jobClass, String time, Map<String,Object> parameter) {

        removeJob(jobName, jobGroupName, triggerGroupName);
        addJob(jobName, jobGroupName, triggerGroupName, jobClass, time, parameter);

    }

    /**移除一个任务(使用默认的任务组名，触发器名，触发器组名)
     * @param jobName	任务名称
     */
    public static void removeJob(String jobName, String jobGroup, String triggerGroupName) {
        try {

            if(StringUtils.isEmpty(jobGroup)){
                jobGroup = JOB_GROUP_NAME;
            }
            if(StringUtils.isEmpty(triggerGroupName)){
                triggerGroupName = TRIGGER_GROUP_NAME;
            }

            Scheduler sched = gSchedulerFactory.getScheduler();
            TriggerKey triggerKey = TriggerKey.triggerKey(jobName,TRIGGER_GROUP_NAME); 	//通过触发器名和组名获取TriggerKey
            JobKey jobKey = JobKey.jobKey(jobName, jobGroup);						    //通过任务名和组名获取JobKey
            sched.pauseTrigger(triggerKey);	// 停止触发器
            sched.unscheduleJob(triggerKey);// 移除触发器
            sched.deleteJob(jobKey);		// 删除任务
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 启动所有定时任务
     */
    public static void startJobs() {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            sched.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 关闭所有定时任务
     */
    public static void shutdownJobs() {
        try {
            Scheduler sched = gSchedulerFactory.getScheduler();
            if (!sched.isShutdown()) {
                sched.shutdown();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}  