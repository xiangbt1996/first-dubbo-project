package com.xiang.provider.service;

import com.xiang.api.bean.UserVO;
import com.xiang.provider.dao.entity.AuthUser;
import com.xiang.provider.dao.mapper.AuthUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    private AuthUserMapper authUserMapper;

    public List<UserVO> getUserList(){
        List<AuthUser> authUsers = authUserMapper.selectByExample(null);
        return authUsers.stream().map(e -> e.conver()).collect(Collectors.toList());
    }
}
