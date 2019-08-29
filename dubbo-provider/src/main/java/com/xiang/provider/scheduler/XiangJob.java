package com.xiang.provider.scheduler;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class XiangJob implements Job {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        try {
            logger.info("XiangJob start.....");
            logger.info("XiangJob end");
        }catch(Exception ex){
            logger.error("",ex);
        }
    }
}
