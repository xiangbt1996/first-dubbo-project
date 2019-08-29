package com.xiang.api.service;


import com.xiang.api.bean.UserVO;

import java.util.List;

public interface DemoService {

    String getIntroductions();
    List<UserVO> getUserList();
}
