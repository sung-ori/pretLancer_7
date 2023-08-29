package com.team.pretLancer_7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.pretLancer_7.service.MypageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("my_page")
public class MypageController {

    @Autowired
    MypageService service;

    @GetMapping("main")
    public String myPageForm(Model m, UserDetails user) {
        
        // MyPage mp =  service.getMyPage(user.getUsername());
        // m.addAttribute("MyPage", mp);
        
        return"/myPageView/MyPage";
    }

}
