package com.xiang.consumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.xiang.api.service.DemoService;
import com.xiang.consumer.service.dubbo.DubboBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class TestController {
    @Autowired
    private DubboBean dubboBean;

    @GetMapping("hello")
    public Object hello() {
        return dubboBean.demoService.getIntroductions();
    }


}
