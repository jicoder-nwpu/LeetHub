package com.jicoder.leethub.controller;

import com.jicoder.leethub.pojo.ProToUser;
import com.jicoder.leethub.service.ProToUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

}
