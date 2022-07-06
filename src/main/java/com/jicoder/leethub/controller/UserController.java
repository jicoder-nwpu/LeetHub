package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.ProblemService;
import com.jicoder.leethub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProblemService problemService;

    @PostMapping ("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model,
                        HttpSession session){
        User user = userService.login(username, password);
        if(user != null){
            session.setAttribute("user", user);
            return "redirect:/user";
        }else{
            model.addAttribute("msg", "用户名或者密码错误！");
            return "login";
        }
    }

    @GetMapping("/login")
    public String login(HttpSession session){
        if(session.getAttribute("user") != null){
            return "redirect:/user";
        }else{
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

    @GetMapping("")
    public String index(Model model){
        Date today = new Date(System.currentTimeMillis());
        Problem problem = problemService.getDailyProblem(today);
        model.addAttribute("todayProblem", problem);
        return "user/index";
    }

    @ResponseBody
    @RequestMapping("/user/{user_id}")
    public User getUser(@PathVariable int user_id){
        return userService.getUser(user_id);
    }

}
