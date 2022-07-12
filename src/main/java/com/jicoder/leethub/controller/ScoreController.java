package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.Score;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    private ScoreService scoreService;


    @ResponseBody
    @PostMapping("/insert")
    public int insert(@RequestBody Score score){
        return scoreService.insert(score);
    }

    @ResponseBody
    @GetMapping("/cur")
    public int getCurScore(HttpSession session){
        User user = (User) session.getAttribute("user");
        return scoreService.getCurScore(user.getUser_id());
    }

    @GetMapping("")
    public String index(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("scores", scoreService.getAllScore(user.getUser_id()));
        model.addAttribute("heads", scoreService.getRankTableHeads());
        return "user/score";
    }

}
