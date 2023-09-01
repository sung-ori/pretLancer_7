package com.team.pretLancer_7.reward;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("reward")
public class RewardController {

    @GetMapping("main")
    public String rewardMain() {
        return "reward";
    }

    // @GetMapping("charge")
    // @ResponseBody
    // public void chargePoint(Long amount) {
    //     System.out.println(amount);
    //     // int id = 
    // }

}
