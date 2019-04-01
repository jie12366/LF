package com.lingfei.admin;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminApplicationTests {
    private static final Logger log = LoggerFactory.getLogger(AdminApplicationTests.class);
    @Autowired
    RedisTemplate redisTemplate;

    @Test
    public void get(){

        String key = "balabala";
        redisTemplate.opsForValue().set(key,"lalala");
        final String k2 = (String)redisTemplate.opsForValue().get(key);
        log.info("[自定义的字符缓存结果] - [{}]",k2);
    }
}
