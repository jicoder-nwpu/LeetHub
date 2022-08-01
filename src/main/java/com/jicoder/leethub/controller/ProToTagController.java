package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.ProToTagService;
import com.jicoder.leethub.service.ProblemService;
import com.jicoder.leethub.service.TagService;
import com.jicoder.leethub.utils.ResponseResult;
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

    @Autowired
    private ProblemService problemService;

    @ResponseBody
    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody Map params){
        int problem_id = Integer.parseInt(String.valueOf(params.get("problem_id")));
        int res = proToTagService.insert(Integer.parseInt(String.valueOf(params.get("tag_id"))), problem_id);
        if(res == -1){
            return new ResponseResult(200, -1, "插入失败!");
        }
        return new ResponseResult(200, 1, "插入成功!");
    }

    @ResponseBody
    @PostMapping("/remove")
    public ResponseResult remove(@RequestBody Map params){
        int problem_id = Integer.parseInt(String.valueOf(params.get("problem_id")));
        int res = proToTagService.remove(Integer.parseInt(String.valueOf(params.get("tag_id"))), problem_id);
        if(res == -1){
            return new ResponseResult(200, -1, "移除失败!");
        }
        return new ResponseResult(200, 1, "移除成功!");
    }

    @GetMapping("/show_tag/{problem_id}")
    public String show_tag(@PathVariable int problem_id, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("used_tags", tagService.getTagByUserAndProblem(user.getUser_id(), problem_id));
        model.addAttribute("problem", problemService.getProblemById(problem_id));
        return "editor::show_tags";
    }

    @GetMapping("/refresh_tag/{problem_id}")
    public String refresh_tag(@PathVariable int problem_id, HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        model.addAttribute("unused_tags", tagService.getUnusedTags(user.getUser_id(), problem_id));
        return "editor::tag_selector";
    }

}
