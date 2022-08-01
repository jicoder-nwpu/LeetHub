package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.Tag;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.ProToTagService;
import com.jicoder.leethub.service.TagService;
import com.jicoder.leethub.utils.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/tag")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("")
    public String label(Model model, HttpSession session){
        User user = (User) session.getAttribute("user");
        model.addAttribute("tags", tagService.getAllTagsByUserId(user.getUser_id()));
        model.addAttribute("countSum", tagService.getCountSum(user.getUser_id()));
        return "tag";
    }

    @ResponseBody
    @PostMapping("/insert")
    public ResponseResult insert(@RequestBody Map params, HttpSession session){
        User user = (User) session.getAttribute("user");
        String name = (String) params.get("name");
        int res = tagService.insert(new Tag(name, 0, user));
        if(res == -1){
            return new ResponseResult(200, -1, "添加Tag失败！");
        }else if(res == -2){
            return new ResponseResult(200, -2, "该标签已存在");
        }else{
            return new ResponseResult(200, 1, "成功添加Tag！");
        }
    }

    @GetMapping("/delete/{tag_id}")
    public String deleteById(@PathVariable int tag_id){
        int res = tagService.deleteById(tag_id);
        return "redirect:/tag";
    }


    @ResponseBody
    @PostMapping("/update_name")
    public ResponseResult update(@RequestBody Tag tag, HttpSession session){
        User user = (User) session.getAttribute("user");
        int res = tagService.getByNameAndUserId(tag.getName(), user.getUser_id());
        if (res < 0){
            return new ResponseResult(200, -2, "该标签已存在！");
        }
        res = tagService.updateTagName(tag.getName(), tag.getTag_id());
        if(res == 1){
            return new ResponseResult(200, 1, "更新成功！");
        }else{
            return new ResponseResult(200, -1, "更新失败！");
        }
    }

}
