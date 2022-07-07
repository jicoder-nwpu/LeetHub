package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.LeetRank;
import com.jicoder.leethub.service.LeetRankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
