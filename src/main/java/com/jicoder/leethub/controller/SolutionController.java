package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.Problem;
import com.jicoder.leethub.pojo.Solution;
import com.jicoder.leethub.pojo.Tag;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.ProToUserService;
import com.jicoder.leethub.service.ProblemService;
import com.jicoder.leethub.service.SolutionService;
import com.jicoder.leethub.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/solution")
public class SolutionController {

    @Autowired
    private SolutionService solutionService;

    @Autowired
    private ProblemService problemService;

    @Autowired
    private TagService tagService;

    @Autowired
    private ProToUserService proToUserService;

    @GetMapping("/editor/{problem_id}")
    public String editor(@PathVariable int problem_id, Model model, HttpSession session){
        User user = (User)session.getAttribute("user");
        Problem problem = problemService.getProblemById(problem_id);
        if(problem == null){
            return "error/404";
        }
        String alias = proToUserService.selectAliasById(user.getUser_id(), problem_id);
        model.addAttribute("alias", alias);
        List<Tag> tags = tagService.getTagByUserAndProblem(user.getUser_id(), problem_id);
        model.addAttribute("used_tags", tags);
        model.addAttribute("unused_tags", tagService.getUnusedTags(user.getUser_id(), problem_id));
        model.addAttribute("problem", problem);
        Solution solution = solutionService.getByPidAndUid(problem_id, user.getUser_id());
        if(solution != null){
            model.addAttribute("solution", solution);
        }else{
            Solution new_solution = new Solution("标题", "题解", new Timestamp(new Date().getTime()), Solution.SOLUTION_DRAFT_TYPE, user, problem);
            int res = solutionService.insert(new_solution);
            if(res == -1){
                return "error/500";
            }
            model.addAttribute("solution", new_solution);
        }
        model.addAttribute("type", 0);
        return "solution/editor";
    }

    @PostMapping("/insert")
    public String insert(@RequestParam("title") String title,
                         @RequestParam("alias") String alias,
                         @RequestParam("context") String context,
                         @RequestParam("problem_id") int problem_id,
                         HttpSession session){
        User user = (User) session.getAttribute("user");
        proToUserService.updateAlias(user.getUser_id(), problem_id, alias);
        Solution old_solution = solutionService.getByPidAndUid(problem_id, user.getUser_id());
        Solution solution = new Solution(title, context, new Timestamp(new Date().getTime()), Solution.SOLUTION_FORMAL_TYPE, user, problemService.getProblemById(problem_id));
        if(old_solution == null){
            return "error/500";
        }else if(!old_solution.equals(solution)){
            int res = solutionService.update(solution);
            if(res == -1){
                return "error/500";
            }
            return "redirect:/solution/" + old_solution.getSolution_id();
        }
        return "redirect:/solution/" + old_solution.getSolution_id();
    }

    @ResponseBody
    @PostMapping("/save")
    public int save(@RequestBody Map params,
                    HttpSession session){
        int problem_id = Integer.parseInt((String) params.get("problem_id"));
        String title = (String) params.get("title");
        String alias = (String) params.get("alias");
        String context = (String) params.get("context");
        User user = (User) session.getAttribute("user");
        proToUserService.updateAlias(user.getUser_id(), problem_id, alias);
        Solution old_solution = solutionService.getByPidAndUid(problem_id, user.getUser_id());
        if(old_solution == null){
            return -1;
        }
        Solution solution = new Solution(title, context, new Timestamp(new Date().getTime()), old_solution.getType(), user, problemService.getProblemById(problem_id));
        if(!old_solution.equals(solution)){
            int res = solutionService.update(solution);
            if(res != 1){
                return -1;
            }
        }
        return 1;
    }

    @GetMapping("/{solution_id}")
    public String show(@PathVariable int solution_id, Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        Solution solution = solutionService.getById(solution_id);
        if(solution == null || solution.getType() == Solution.SOLUTION_DRAFT_TYPE){
            return "error/404";
        }else{
            model.addAttribute("solution", solution);
            model.addAttribute("type", 1);
            List<Solution> solutions = solutionService.getLatestByUid(user.getUser_id(), solution_id, Solution.SOLUTION_COUNT, Solution.SOLUTION_FORMAL_TYPE);
            if(solutions == null || solutions.size() <= 0){
                return "solution/show";
            }
            model.addAttribute("solutions", solutions);
            return "solution/show";
        }
    }

    @GetMapping("/all")
    public String all(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<Solution> solutions = solutionService.getAllByUid(user.getUser_id());
        model.addAttribute("type", 1);
        if(solutions == null || solutions.size() <= 0){
            return "solution/all";
        }
        model.addAttribute("solutions", solutions);
        List<Tag> tags = tagService.getAllTagsByUserId(user.getUser_id());
        if(tags == null || tags.size() <= 0){
            return "solution/all";
        }
        model.addAttribute("tags", tags);
        return "solution/all";
    }

    @PostMapping("/searchl")
    public String searchByLabel(@RequestBody Map params,
                                HttpSession session,
                                Model model){
        User user = (User) session.getAttribute("user");
        int tag_id = Integer.parseInt((String) params.get("tag_id"));
        List<Solution> solutions = solutionService.getByTag(user.getUser_id(), tag_id);
        if(solutions.size() > 0){
            model.addAttribute("solutions", solutions);
        }
        List<Tag> tags = tagService.getAllTagsByUserId(user.getUser_id());
        if(tags.size() > 0){
            model.addAttribute("tags", tags);
        }
        return "solution/all::show_solutions";
    }

    @PostMapping("/searchn")
    public String searchByname(@RequestBody Map params,
                               HttpSession session,
                               Model model){
        User user = (User) session.getAttribute("user");
        String name = (String) params.get("name");
        List<Solution> solutions = solutionService.getByName(user.getUser_id(), "%" + name + "%");
        if(solutions.size() > 0){
            model.addAttribute("solutions", solutions);
        }
        List<Tag> tags = tagService.getAllTagsByUserId(user.getUser_id());
        if(tags.size() > 0){
            model.addAttribute("tags", tags);
        }
        return "solution/all::show_solutions";
    }

}
