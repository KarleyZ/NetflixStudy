package com.ling.service.impl;

import com.ling.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getUserInfo(String userId) {
        if(userId.equals("123")){
            return "18849221699";
        }else{
            return "13520396462";
        }
    }
}
