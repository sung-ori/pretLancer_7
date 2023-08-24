package com.team.pretLancer_7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.pretLancer_7.service.MypageService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("my_page")
public class MypageController {

    @Autowired
    MypageService service;

    // @GetMapping({" ","/"})
    // public String myPageForm(Model m) {


        
    //     return"/myPageView/MyPage";
    // }

}
