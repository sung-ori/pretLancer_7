package com.team.pretLancer_7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MypageController {

    @Autowired

    @GetMapping("my_page")
    public String myPageForm() {

        
        return"/myPageView/MyPage";
    }

}
