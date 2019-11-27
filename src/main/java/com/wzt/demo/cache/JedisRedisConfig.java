package com.wzt.demo.cache;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author wangzitao
 * @create 2018-05-17 17:06
 **/
@Configuration
@PropertySource(value = "classpath:/application.properties")
public class JedisRedisConfig {
    @Value("${spring.redis.host}")
    private  String host;
    @Value("${spring.redis.password}")
    private  String password;
    @Value("${spring.redis.port}")
    private  int port;


    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        /**
         *设置连接超时时间
         **/
        factory.setTimeout(1000);
        return factory;
    }
}