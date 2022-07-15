package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private ProblemService problemService;

    @RequestMapping("")
    public String hello(HttpSession session){
        if(session.getAttribute("user") != null){
            return "redirect:/user";
        }
        return "login";
    }

    @GetMapping("editor/{problem_id}")
    public String editor(@PathVariable int problem_id, Model model){
        Problem res = problemService.getProblemById(problem_id);
        model.addAttribute("problem", res);
        return "editor";
    }

}
