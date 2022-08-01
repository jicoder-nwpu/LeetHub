package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.ProToTagService;
import com.jicoder.leethub.service.TagService;
import com.jicoder.leethub.utils.ResponseTagList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/ptt")
public class ProToTagController {

    @Autowired
    private ProToTagService proToTagService;

    @Autowired
    private TagService tagService;

    @PostMapping("/insert")
    public String insert(@RequestBody Map params, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        int problem_id = Integer.parseInt(String.valueOf(params.get("problem_id")));
        int res = proToTagService.insert(Integer.parseInt(String.valueOf(params.get("tag_id"))), problem_id);
        model.addAttribute("used_tags", tagService.getTagByUserAndProblem(user.getUser_id(), problem_id));
        return "editor::show_tags";
    }

    @GetMapping("/refresh_tag/{problem_id}")
    public String refresh_tag(@PathVariable int problem_id, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("unused_tags", tagService.getUnusedTags(user.getUser_id(), problem_id));
        return "editor::tag_selector";
    }

}
