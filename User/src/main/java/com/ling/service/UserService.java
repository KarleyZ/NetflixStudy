package com.ling.service;

import org.springframework.stereotype.Service;

@Service
public interface UserService {

    public String getUserInfo(String userId);
}
