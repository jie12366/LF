package com.lingfei.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
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
@EnableSwagger2
@EnableElasticsearchRepositories
public class AdminApplication {

    public static void main(String[] args) {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(AdminApplication.class, args);
    }

}
