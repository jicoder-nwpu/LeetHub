package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.pojo.Solution;
import com.jicoder.leethub.pojo.Tag;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.ProblemService;
import com.jicoder.leethub.service.SolutionService;
import com.jicoder.leethub.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/")
public class HelloController {

    @Autowired
    private ProblemService problemService;

    @Autowired
    private TagService tagService;

    @Autowired
    private SolutionService solutionService;

    @RequestMapping("")
    public String hello(HttpSession session){
        if(session.getAttribute("user") != null){
            return "redirect:/user";
        }
        return "login";
    }

    @GetMapping("editor/{problem_id}")
    public String editor(@PathVariable int problem_id, Model model, HttpSession session){
        User user = (User)session.getAttribute("user");
        Problem res = problemService.getProblemById(problem_id);
        if(res == null){
            return "error/404";
        }
        List<Tag> tags = tagService.getTagByUserAndProblem(user.getUser_id(), problem_id);
        model.addAttribute("used_tags", tags);
        model.addAttribute("unused_tags", tagService.getUnusedTags(user.getUser_id(), problem_id));
        model.addAttribute("problem", res);
        Solution solution = solutionService.getByPidAndUid(problem_id, user.getUser_id());
        if(solution != null){
            model.addAttribute("solution", solution);
        }
        return "editor";
    }

}
