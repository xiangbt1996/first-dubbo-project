package com.xiang.provider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiang.api.service.DemoService;

import java.util.ArrayList;
import java.util.List;

@Service(version = "1.0.0")
public class DemoServiceImpl implements DemoService {
    @Override
    public String getIntroductions() {
        return "xiang的第一个dubbo项目";
    }
}
