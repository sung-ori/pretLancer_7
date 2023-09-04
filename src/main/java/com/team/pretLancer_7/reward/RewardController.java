package com.team.pretLancer_7.reward;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.pretLancer_7.domain.Reward;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("reward")
public class RewardController {

    @Autowired
    RewardService service;

    @GetMapping("main")
    public String rewardMain() {
        return "reward";
    }

    @GetMapping("charge")
    @ResponseBody
    public void chargePoint(Reward reward, @AuthenticationPrincipal UserDetails user) {
        reward.setMemberid(user.getUsername());
        System.out.println(reward.getAmount());
        System.out.println(reward.getPayment_method());
    
        service.charge(reward);
        
    }

}