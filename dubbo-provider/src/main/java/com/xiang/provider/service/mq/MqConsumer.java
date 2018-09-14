package com.xiang.provider.service.mq;

import com.xiang.api.util.MQTopicConst;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MqConsumer {

    /**
     * 收到彩期的开奖结果
     * @param msg
     */
    @JmsListener(destination = MQTopicConst.FIRST_TOPIC_MQ, containerFactory = "jmsListenerContainerTopic")
    public void revMyFirstMq(String msg) {
        System.out.println("接受到到："+msg);
    }
}
