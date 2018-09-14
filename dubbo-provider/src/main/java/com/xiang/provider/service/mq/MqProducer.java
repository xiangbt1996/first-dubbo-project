package com.xiang.provider.service.mq;

import com.google.gson.Gson;
import com.xiang.api.util.MQTopicConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class MqProducer {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic mqTopicFirst;

    public void sendMyFirstMq(String msg){
        logger.info("sendMyFirstMq | "+MQTopicConst.FIRST_TOPIC_MQ + "=" + msg);
        this.jmsMessagingTemplate.convertAndSend(this.mqTopicFirst,msg);
    }
}
