package com.xiang.provider.service.dubbo;

import com.alibaba.dubbo.config.annotation.Service;
import com.xiang.api.bean.UserVO;
import com.xiang.api.service.DemoService;
import com.xiang.provider.service.UserService;
import com.xiang.provider.service.common.RedisService;
import com.xiang.provider.service.mq.MqProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

@Service(
        version = "1.0.0",
        application = "${dubbo.application.id}",
        protocol = "${dubbo.protocol.id}",
        registry = "${dubbo.registry.id}"
)
public class DemoServiceImpl implements DemoService {
    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Autowired
    private MqProducer mqProducer;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserService userService;
    @Override
    public String getIntroductions(){
        mqProducer.sendMyFirstMq("DemoServiceImpl发送第一个mq消息");
        Jedis jedis = null;
        String value = null;
        try{
            jedis =  redisService.getJedisPool().getResource();
            value = jedis.get("hello");
        }catch (Exception e){
            value = e.getMessage();
            logger.error(e.getMessage(),e);
        }finally {
            if(jedis != null){
                jedis.close();
            }
        }
        logger.info("12345");
        return value;
    }

    @Override
    public List<UserVO> getUserList() {
        return userService.getUserList();
    }
}
