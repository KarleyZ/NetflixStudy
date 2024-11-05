package com.ling.controller;


import com.ling.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserController {

    @Value("${server.port}")
    private String port;

    @Autowired
    private UserService userService;

    @RequestMapping("/test")
    public String test(){
        return "Get user information: "+port+" successfully!";
    }

    @RequestMapping("/getUserInfo/{userId}")
    public String getUser(@PathVariable String userId){
        return userService.getUserInfo(userId);
    }
}
