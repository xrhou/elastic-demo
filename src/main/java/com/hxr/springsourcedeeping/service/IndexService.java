package com.hxr.springsourcedeeping.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author houxiurong
 * @date 2019-10-19
 */
@Service
public class IndexService {

    @Autowired
    OrderService orderService;

    public IndexService() {
        System.out.println("default IndexService init success....");
    }

    public void getOrderService() {
        System.out.println(orderService);
    }
}
