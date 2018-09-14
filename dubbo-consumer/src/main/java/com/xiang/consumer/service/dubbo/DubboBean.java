package com.xiang.consumer.service.dubbo;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiang.api.service.DemoService;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DubboBean {
    @Reference(version = "1.0.0",
            application = "${dubbo.application.id}"
    )
    public DemoService demoService;
}
