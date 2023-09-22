package com.team.pretLancer_7.reward;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.pretLancer_7.dao.MemberDAO;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.Reward;
import com.team.pretLancer_7.email.EmailServiceImpl;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("reward")
public class RewardController {

    @Autowired
    RewardService service;
    @Autowired
    MemberDAO mdao;
    
    @Autowired
    EmailServiceImpl eservice;

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

    @GetMapping("paybackForm")
    public String paybackForm(@AuthenticationPrincipal UserDetails user,Model model) {
        Member userInfo = mdao.selectOne(user.getUsername());
        model.addAttribute("userinfo", userInfo);

        return"/paybackForm";
    }

    @GetMapping("checkCash")
    @ResponseBody
    public int checkCash(@AuthenticationPrincipal UserDetails user) {

        int cash = mdao.selectOne(user.getUsername()).getCash();
        cash = (int)(cash - cash*(0.15));

        return cash;
    }

    @GetMapping("requestPayback")
    @ResponseBody
    public void requestPayback(@AuthenticationPrincipal UserDetails user, 
                            @RequestParam(name="cash") int cash){
        String userid = user.getUsername();
        Member userinfo = mdao.selectOne(userid);
        int realcash = (int)(cash - cash*(0.15));

        service.payback(userid, cash);

        eservice.sendPayEmail(userinfo, realcash);




    }
    
}
