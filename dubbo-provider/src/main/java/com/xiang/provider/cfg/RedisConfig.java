package com.xiang.provider.cfg;

import com.google.gson.Gson;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Created by zhangbing on 01/05/2017.
 */
@Component
@ConfigurationProperties(prefix="spring.redis")
public class RedisConfig {

    private String  hostName;
    private Integer port;
    private Integer timeout;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public Integer getTimeout() {
        return timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    @Override
    public String toString(){
        return new Gson().toJson(this, RedisConfig.class).toString();
    }
}
