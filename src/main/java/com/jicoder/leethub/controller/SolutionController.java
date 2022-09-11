package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.Solution;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.ProblemService;
import com.jicoder.leethub.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.sql.Timestamp;
import java.util.Map;

@Controller
@RequestMapping("/solution")
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private ProblemService problemService;

    @PostMapping("/insert")
    public String insert(@RequestParam("title") String title,
                         @RequestParam("context") String context,
                         @RequestParam("problem_id") int problem_id,
                         HttpSession session,
                         Model model){
        User user = (User) session.getAttribute("user");
        Solution old_solution = solutionService.getByPidAndUid(problem_id, user.getUser_id());
        Solution solution = new Solution(title, context, new Timestamp(new Date().getTime()), user, problemService.getProblemById(problem_id));
        if(old_solution == null){
            int res = solutionService.insert(solution);
            if(res != 1){
                return "error/500";
            }
        }else if(!old_solution.equals(solution)){
            int res = solutionService.update(solution);
            if(res != 1){
                return "error/500";
            }
        }else{
            model.addAttribute("solution", old_solution);
            return "solution/show";
        }
        model.addAttribute("solution", solution);
        return "solution/show";
    }

    @ResponseBody
    @PostMapping("/save")
    public int save(@RequestBody Map params,
                    HttpSession session){
        int problem_id = Integer.parseInt((String) params.get("problem_id"));
        String title = (String) params.get("title");
        String context = (String) params.get("context");
        User user = (User) session.getAttribute("user");
        Solution old_solution = solutionService.getByPidAndUid(problem_id, user.getUser_id());
        Solution solution = new Solution(title, context, new Timestamp(new Date().getTime()), user, problemService.getProblemById(problem_id));
        if(old_solution == null){
            int res = solutionService.insert(solution);
            if(res != 1){
                return -1;
            }
        }else if(!old_solution.equals(solution)){
            int res = solutionService.update(solution);
            if(res != 1){
                return -1;
            }
        }
        return 1;
    }

    @GetMapping("/{solution_id}")
    public String show(@PathVariable int solution_id, Model model){
        Solution solution = solutionService.getById(solution_id);
        if(solution == null){
            return "error/404";
        }else{
            model.addAttribute("solution", solution);
            return "solution/show";
        }
    }

}
