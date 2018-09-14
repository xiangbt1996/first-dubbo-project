package com.xiang.provider.service.mq;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class MqProducer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Topic mqTopicFirst;

    public void sendMyFirstMq(String msg){
        this.jmsMessagingTemplate.convertAndSend(this.mqTopicFirst,msg);
    }
}
