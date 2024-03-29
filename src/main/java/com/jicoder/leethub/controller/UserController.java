package com.jicoder.leethub.controller;

import com.alibaba.fastjson.JSON;
import com.jicoder.leethub.pojo.*;
import com.jicoder.leethub.service.*;
import com.jicoder.leethub.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private LeetRankService leetRankService;

    @Autowired
    private ProToUserService proToUserService;

    @Autowired
    private ScoreService scoreService;


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
    public String index(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");

        Problem problem = problemService.getDailyProblem();
        model.addAttribute("todayProblem", problem);

        model.addAttribute("commonRank", JSON.toJSONString(leetRankService.getCharData(user, LeetRank.DailyRank, LeetRank.IndexRankNum)));

        model.addAttribute("contestRankData", JSON.toJSONString(leetRankService.getCharData(user, LeetRank.ContestRank, LeetRank.IndexRankNum)));

        List<Score> scores = scoreService.getRecentScore(user.getUser_id());
        List<Integer> score_vals = scoreService.getScoreVals(scores);
        List<String> score_dates = scoreService.getDates(scores);
        LineChartData scoreData = new LineChartData(score_dates, score_vals);
        model.addAttribute("scoreData", JSON.toJSONString(scoreData));

        model.addAttribute("hasDailyProblemRecord", proToUserService.hasDailyProblemRecord(problem, user));

        model.addAttribute("dailyPCount", userService.selectDailyPCount(user.getUser_id()));

        model.addAttribute("dailySummayData", JSON.toJSONString(leetRankService.getTodaySummay(user.getUser_id())));
        return "user/index";
    }

    @ResponseBody
    @PostMapping("/update/dailypcount")
    public int updateDailyPCount(@RequestBody Map params){
        return userService.updateDailyPCount((int)params.get("user_id"), (int)params.get("count"));
    }

    @GetMapping("/profile")
    public String profile(HttpSession session, Model model){
        model.addAttribute("user", session.getAttribute("user"));
        return "user/setting";
    }


    @ResponseBody
    @GetMapping("/info")
    public User getUserInfo(HttpSession session){
        return (User) session.getAttribute("user");
    }

    @ResponseBody
    @PostMapping("/check")
    public ResponseResult checkUsername(@RequestBody Map params){
        String username = (String) params.get("username");
        User user = userService.getUserByName(username);
        if(user == null){
            return new ResponseResult(200, 1, "ok");
        }else{
            return new ResponseResult(200, -1, "no");
        }
    }

}
