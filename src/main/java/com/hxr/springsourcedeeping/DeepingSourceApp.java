package com.hxr.springsourcedeeping;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * spring源码深入理解-循环依赖
 *
 * @author houxiurong
 * @date 2019-10-19
 */
@ComponentScan("com.hxr.springsourcedeeping")
public class DeepingSourceApp {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(DeepingSourceApp.class);
        Object orderService = ac.getBean("orderService");
    }
}
