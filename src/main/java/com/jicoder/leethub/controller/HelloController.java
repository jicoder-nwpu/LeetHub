package com.jicoder.leethub.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HelloController {

    @RequestMapping("")
    public String hello(HttpSession session){
        if(session.getAttribute("user") == null){
            return "home";
        }else{
            return "redirect:/user";
        }
    }

    @RequestMapping("home")
    public String index(HttpSession session){
        if(session.getAttribute("user") == null){
            return "home";
        }else{
            return "redirect:/user";
        }
    }

}
