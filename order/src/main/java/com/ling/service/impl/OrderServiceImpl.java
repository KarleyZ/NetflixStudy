package com.ling.service.impl;

import com.ling.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    /**
     * 在这里就要调用Eureka来获取user电话
     * @return
     */
//    @Autowired
//    private LoadBalancerClient eurekaClient;


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    /**
     * eureka的远程微服务调用
     * @param userId
     * @return
     */
    /*@Override
    public String createOrder(String userId) {
        //获取User实例
        ServiceInstance instance = eurekaClient.choose("User");
        String host = instance.getHost();
        int port = instance.getPort();
        String url = "http://"+host+":"+port+"/getUserInfo?userId="+userId;
        log.info("请求获取用户信息的拼接url: "+ url);
        String result = restTemplate.getForObject(url, String.class);
        return "成功创建订单，用户电话为："+ result;
    }*/

    @Override
    public String createOrder(String userId) {
        //获取所有user服务实例
        List<ServiceInstance> userInstances = discoveryClient.getInstances("User");
        //获取所有服务实例的uri
        List<String> targetUrls = userInstances.stream().map(instances -> instances.getUri().toString()
                + "/getUserInfo?userId=").collect(Collectors.toList());
        targetUrls.forEach(url -> log.info("user的targetUrls是：" + url));
        //随机选择一个uri
        int i = ThreadLocalRandom.current().nextInt(targetUrls.size());
        String targetUrl = targetUrls.get(i);
        log.info("最终选择的url是：{}",targetUrl + userId);
        String result = restTemplate.getForObject(targetUrl, String.class);
        log.info("获取用户信息成功");
        return "成功创建订单，用户电话为："+ result;
    }
}
