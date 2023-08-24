package com.team.pretLancer_7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class RewardController {
    
    @GetMapping("reward_page")
    public String reawarPage() {
        return "/rewardView/rewardForm";
    } 
}
