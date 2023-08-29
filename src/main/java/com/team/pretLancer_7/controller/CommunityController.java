package com.team.pretLancer_7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("community")
public class CommunityController {
    
    @GetMapping("main")
    public String commyMain() {

		return ("/communityForm/mainPage");
    }
}
