package com.ling.service.impl;

import com.ling.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    /**
     * 在这里就要调用Eureka来获取user电话
     * @return
     */
    @Autowired
    private LoadBalancerClient eurekaClient;

    @Autowired
    private RestTemplate restTemplate;
    
    @Override
    public String createOrder(String userId) {
        //获取User实例
        ServiceInstance instance = eurekaClient.choose("User");
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://"+host+":"+port+"/getUserInfo/"+userId;
        log.info("请求获取用户信息的拼接url: "+ url);
        String result = restTemplate.getForObject(url, String.class);
        return "成功创建订单，用户电话为："+ result;
    }
}
