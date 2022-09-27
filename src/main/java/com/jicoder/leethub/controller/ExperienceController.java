package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.Experience;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.ExperienceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/experience")
public class ExperienceController {

    @Autowired
    private ExperienceService experienceService;

    @GetMapping("/editor")
    public String index(Model model){
        model.addAttribute("labels", experienceService.getLabels());
        model.addAttribute("id", -1);
        return "experience/editor";
    }

    @GetMapping("/{experience_id}")
    public String detail(@PathVariable int experience_id,
                         Model model){
        Experience experience = experienceService.getById(experience_id);
        if(experience == null){
            return "error/404";
        }
        model.addAttribute("labels", experienceService.getLabels());
        model.addAttribute("id", experience.getExperience_id());
        model.addAttribute("experience", experience);
        return "experience/editor";
    }

    @ResponseBody
    @PostMapping("/insert")
    public int insert(@RequestParam("title") String title,
                      @RequestParam("content") String content,
                      @RequestParam("label") String label,
                      @RequestParam("experience_id") int experience_id,
                      HttpSession session){
        User user = (User) session.getAttribute("user");
        int res = -1;
        Experience experience = new Experience(title, content, label, user, Experience.EXPERIENCE_TYPE_RELEASE);
        if(experience_id == Experience.EXPERIENCE_ID_NULL){
            res = experienceService.insert(experience);
        }else{
            Experience old_experience = experienceService.getById(experience_id);
            if(old_experience.equals(experience)){
                res = 1;
            }else{
                old_experience.setTitle(title);
                old_experience.setContent(content);
                old_experience.setLabel(label);
                old_experience.setType(Experience.EXPERIENCE_TYPE_RELEASE);
                res = experienceService.update(old_experience);
            }
        }
        return res;
    }

    @ResponseBody
    @PostMapping("/save")
    public int save(@RequestBody Map params,
                      HttpSession session){
        String title = (String) params.get("title");
        String content = (String) params.get("content");
        String label = (String) params.get("label");
        int experience_id = Integer.parseInt((String) params.get("experience_id"));
        User user = (User) session.getAttribute("user");
        int res = -1;
        if(experience_id == Experience.EXPERIENCE_ID_NULL){
            Experience experience = new Experience(title, content, label, user, Experience.EXPERIENCE_TYPE_DRAFT);
            experienceService.insert(experience);
            res = experience.getExperience_id();
        }else{
            Experience old_experience = experienceService.getById(experience_id);
            if(old_experience.equals(new Experience(title, content, label, user, old_experience.getType()))){
                res = old_experience.getExperience_id();
            }else{
                old_experience.setTitle(title);
                old_experience.setContent(content);
                old_experience.setLabel(label);
                experienceService.update(old_experience);
                res = old_experience.getExperience_id();
            }
        }
        return res;
    }


}
