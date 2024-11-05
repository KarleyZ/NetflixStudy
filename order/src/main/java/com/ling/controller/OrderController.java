package com.ling.controller;

import com.ling.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController {

    //1.使用Eureka实现调用User的getUserInfo方法,默认获取userId然后下订单，实际肯定不这样
    @Autowired
    private OrderService orderService;
    @RequestMapping("/addOrder/{userId}")
    public String createOrder(@PathVariable String userId){

        return orderService.createOrder(userId);
    }
}
