package com.xiang.provider.scheduler;

import com.xiang.api.service.task.QuartzManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class XiangSchedule {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
    @PostConstruct
    public void init(){
        try {
            QuartzManager.addJob("XiangJob",
                    XiangJob.class,
                    "0 0 12 * * ?",
                    null);
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
        }
    }
}
