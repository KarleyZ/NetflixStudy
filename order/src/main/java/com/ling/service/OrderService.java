package com.ling.service;

import org.springframework.stereotype.Service;

@Service
public interface OrderService {

    public String createOrder(String userId);
}
