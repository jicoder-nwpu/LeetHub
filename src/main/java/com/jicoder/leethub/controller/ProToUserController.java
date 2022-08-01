package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.ProToUser;
import com.jicoder.leethub.pojo.User;
import com.jicoder.leethub.service.ProToUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/pu")
public class ProToUserController {

    @Autowired
    private ProToUserService proToUserService;

    @ResponseBody
    @PostMapping("/insert")
    public int insertPU(@RequestBody ProToUser proToUser){
        return proToUserService.insertPU(proToUser);
    }

    @GetMapping("/all")
    public String all(HttpSession session, Model model){
        User user = (User) session.getAttribute("user");
        List<ProToUser> all = proToUserService.getAllByUser(user.getUser_id());
        model.addAttribute("pus", all);
        return "user/record";
    }

}
