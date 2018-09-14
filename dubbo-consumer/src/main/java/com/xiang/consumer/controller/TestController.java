package com.xiang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiang.api.service.DemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @Reference(version = "1.0.0")
    private DemoService demoService;

    @GetMapping("hello")
    public Object hello() {
        return demoService.getIntroductions();
    }


}
