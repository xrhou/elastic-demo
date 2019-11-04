package com.hxr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 程序启动入口
 *
 * @author houxiurong
 * @date 2019-07-26
 */
@SpringBootApplication(scanBasePackages = {"com.hxr"})
@EnableAsync
public class ElasticDemoApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ElasticDemoApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(ElasticDemoApplication.class, args);
    }
}
