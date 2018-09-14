package com.xiang.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiang.api.service.DemoService;
import com.xiang.provider.service.mq.MqProducer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DemoServiceImpl implements DemoService {
    @Autowired
    private MqProducer mqProducer;
    @Override
    public String getIntroductions() {
        mqProducer.sendMyFirstMq("DemoServiceImpl发送第一个mq消息");
        return "xiang的第一个dubbo项目✌️";
    }
}
