package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.UserService;
import com.jicoder.leethub.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private UserService userService;

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

    @GetMapping("logup")
    public String register(){
        return "register";
    }

    @ResponseBody
    @PostMapping("logup")
    public ResponseResult logup(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        @RequestParam("email") String email,
                        HttpSession session){
        User user = new User(username, password, email);
        userService.inserUser(user);
        session.setAttribute("user", user);
        return new ResponseResult(200, 1, "ok");
    }

}
