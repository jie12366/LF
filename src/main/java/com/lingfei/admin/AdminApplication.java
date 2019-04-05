package com.lingfei.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * 主程序入口
 * @author 熊义杰
 * @date 2019-3-16
 */

@MapperScan("com.lingfei.admin.mapper")
//@MapperScan("microservice.qssj.mapper")
@SpringBootApplication
@EnableRedisHttpSession
@EnableCaching
@EnableSwagger2
@EnableElasticsearchRepositories
@EnableTransactionManagement(proxyTargetClass = true)
public class AdminApplication {

    public static void main(String[] args) {
        //解决es报错
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(AdminApplication.class, args);
    }

}
