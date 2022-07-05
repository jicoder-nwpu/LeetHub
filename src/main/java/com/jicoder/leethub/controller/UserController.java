package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session){
        if(session.getAttribute("user") != null){
            return "redirect:/";
        }
        User user = userService.login(username, password);
        if(user != null){
            session.setAttribute("user", user);
            return "redirect:/";
        }else{
            model.addAttribute("msg", "用户名或者密码错误！");
            return "login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        try{
            session.removeAttribute("user");
            session.invalidate();
            return "redirect:/";
        }catch (Exception e){
            return "login";
        }
    }

    @ResponseBody
    @RequestMapping("/user/{user_id}")
    public User getUser(@PathVariable int user_id){
        return userService.getUser(user_id);
    }

}
