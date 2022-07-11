package com.jicoder.leethub.controller;

import com.alibaba.fastjson.JSON;
import com.jicoder.leethub.pojo.LeetRank;
import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.pojo.Ranks;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.LeetRankService;
import com.jicoder.leethub.service.ProToUserService;
import com.jicoder.leethub.service.ProblemService;
import com.jicoder.leethub.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
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

        List<LeetRank> daily_ranks = leetRankService.getRecentRank(user, LeetRank.DailyRank, LeetRank.IndexRankNum);
        List<Date> daily_rank_dates = leetRankService.getRecentDates(daily_ranks);
        List<Integer> daily_rank_vals = leetRankService.getRecentVals(daily_ranks);
        Ranks commonRank = new Ranks(daily_rank_dates, daily_rank_vals);
        model.addAttribute("commonRank", JSON.toJSONString(commonRank));

        model.addAttribute("hasDailyProblemRecord", proToUserService.hasDailyProblemRecord(problem, user));

        model.addAttribute("dailyPCount", userService.selectDailyPCount(user.getUser_id()));
        return "user/index";
    }

    @ResponseBody
    @PostMapping("/update/dailypcount")
    public int updateDailyPCount(@RequestBody Map params){
        return userService.updateDailyPCount((int)params.get("user_id"), (int)params.get("count"));
    }


    @RequestMapping("/rank/{type}")
    public String rank(@PathVariable int type, Model model){
        List<String> heads = userService.getRankTableHeads();
        model.addAttribute("heads", heads);

        List<LeetRank> ranks = leetRankService.getAllRank(type);
        model.addAttribute("ranks", ranks);
        return "user/rank";
    }

    @ResponseBody
    @GetMapping("/info")
    public User getUserInfo(HttpSession session){
        return (User) session.getAttribute("user");
    }

    @ResponseBody
    @RequestMapping("/user/{user_id}")
    public User getUser(@PathVariable int user_id){
        return userService.getUser(user_id);
    }

}
