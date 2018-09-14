package com.xiang.provider.service.mq;

import com.xiang.api.util.MQTopicConst;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class MqConsumer {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    /**
     * 收到彩期的开奖结果
     * @param msg
     */
    @JmsListener(destination = MQTopicConst.FIRST_TOPIC_MQ, containerFactory = "jmsListenerContainerTopic")
    public void revMyFirstMq(String msg) {
        logger.info("revMyFirstMq  | "+MQTopicConst.FIRST_TOPIC_MQ + "=" + msg);
    }
}
