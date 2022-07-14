package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.LeetRank;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.LeetRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/rank")
public class RankController {

    @Autowired
    private LeetRankService leetRankService;

    @ResponseBody
    @PostMapping("/insert")
    public int insertRank(@RequestBody LeetRank rank){
        return leetRankService.insertRank(rank);
    }

    @ResponseBody
    @GetMapping("/latest/{type}")
    public int selectLatestRank(@PathVariable int type, HttpSession session){
        User user = (User) session.getAttribute("user");
        return leetRankService.selectLatestRank(user.getUser_id(), type);
    }

    @RequestMapping("/{type}")
    public String rank(@PathVariable int type, Model model, HttpSession session){
        if(type == 0){
            model.addAttribute("rankType", 0);
            model.addAttribute("heads", leetRankService.getDailyRankTableHeads());
        }else if(type == 1){
            model.addAttribute("rankType", 1);
            model.addAttribute("heads", leetRankService.getContestRankTableHeads());
        }
        User user = (User) session.getAttribute("user");
        List<LeetRank> ranks = leetRankService.getAllRankByType(user.getUser_id(), type);
        model.addAttribute("ranks", ranks);
        return "user/rank";
    }

    @RequestMapping("/all")
    public String allRank(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("ranks", leetRankService.getAllRank(user.getUser_id()));
        model.addAttribute("heads", leetRankService.getAllRankTableHeads());
        model.addAttribute("rankType", 2);
        return "user/rank";
    }

    @ResponseBody
    @PostMapping ("/count")
    public int getRankCount(@RequestBody Map params, HttpSession session){
        User user = (User) session.getAttribute("user");
        int type = (int) params.get("type");
        Date update_time = Date.valueOf((String) params.get("update_time"));
        if(type == LeetRank.EASYCOUNT){
            return leetRankService.selectEasyCount(update_time, user.getUser_id());
        }else if(type == LeetRank.MEDIUMCOUNT){
            return leetRankService.selectMediumCount(update_time, user.getUser_id());
        }else if(type == LeetRank.HARDCOUNT){
            return leetRankService.selectHardCount(update_time, user.getUser_id());
        }
        return -1;
    }

    @ResponseBody
    @PostMapping("/update")
    public int updateRankCount(@RequestBody Map params,
                               HttpSession session){
        User user = (User) session.getAttribute("user");
        int type = (int) params.get("type");
        int count = (int) params.get("count");
        Date update_time = Date.valueOf((String) params.get("update_time"));
        if(type == LeetRank.EASYCOUNT){
            return leetRankService.updateEasyCount(count, update_time, user.getUser_id());
        }else if(type == LeetRank.MEDIUMCOUNT){
            return  leetRankService.updateMediumCount(count, update_time, user.getUser_id());
        }else if(type == LeetRank.HARDCOUNT){
            return leetRankService.updateHardCount(count, update_time, user.getUser_id());
        }
        return -1;
    }

}
