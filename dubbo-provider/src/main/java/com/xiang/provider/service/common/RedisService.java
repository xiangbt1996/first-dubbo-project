package com.xiang.provider.service.common;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.xiang.provider.cfg.RedisConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
public class RedisService {
    private static Logger logger = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    private RedisConfig redisConfig;

    private static JedisPool jedisPool = null;
    @Bean
    public JedisPool getJedisPool(){
        try{
            if(jedisPool == null){
                String host = redisConfig.getHostName();
                int port = redisConfig.getPort();
                if(StringUtils.isEmpty(host) || port <= 0){
                    throw new Exception("Redis配置错误！");
                }
                JedisPoolConfig config = new JedisPoolConfig();
                // 设置池配置项值
                config.setMaxTotal(256);
                config.setMaxIdle(10);
                config.setMaxWaitMillis(redisConfig.getTimeout());
                logger.info("RedisCache:" + redisConfig.getHostName() + "|port:" + redisConfig.getPort());
                // 根据配置实例化jedis池
                jedisPool = new JedisPool(config, redisConfig.getHostName(), redisConfig.getPort());
            }
            return jedisPool;
        }catch(Exception ex){
            logger.error(ex.getMessage(),ex);
            return null;
        }
    }
}
