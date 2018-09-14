package com.xiang.provider.dao.entity;

import com.xiang.api.bean.UserVO;

import java.util.Date;

public class AuthUser {
    private Long id;

    private String account;

    private String pwd;

    private Byte sex;

    private Date birthday;

    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account == null ? null : account.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UserVO conver(){
        UserVO userVO = new UserVO();
        userVO.setAccount(getAccount());
        userVO.setSex((int)getSex());
        userVO.setBirthday(getBirthday());
        userVO.setId(getId());
        return userVO;
    }
}