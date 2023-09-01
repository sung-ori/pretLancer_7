package com.team.pretLancer_7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("reward")
public class RewardController {
    
    @GetMapping("mainpage")
    public String reawarPage() {
        
        return "/rewardView/mainPage";
    }
}
