package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HelloController {

    @Autowired
    private UserService userService;

    @RequestMapping("/500")
    public String hello(){
        return "500";
    }

    @ResponseBody
    @RequestMapping("/user/{user_id}")
    public User getUser(@PathVariable int user_id){
        return userService.getUser(user_id);
    }

}
