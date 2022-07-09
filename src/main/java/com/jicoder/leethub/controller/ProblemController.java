package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;

@Controller
@RequestMapping("/problem")
public class ProblemController {

    @Autowired
    private ProblemService problemService;


    @ResponseBody
    @PostMapping("/insert")
    public int insertProblem(@RequestBody Problem problem){
//        System.out.println(problem.toString());
        int res = problemService.insertProblem(problem);
        return res;
    }

    @ResponseBody
    @GetMapping("/today")
    public Problem getDailyProblem(){
        Problem problem = problemService.getDailyProblem();
        return problem;
    }

}
