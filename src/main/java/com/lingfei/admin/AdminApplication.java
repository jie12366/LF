package com.lingfei.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 主程序入口
 * @author 熊义杰
 * @date 2019-3-16
 */
@MapperScan("com.lingfei.admin.mapper")
@SpringBootApplication
@EnableRedisHttpSession
@EnableCaching
public class AdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminApplication.class, args);
    }

}
