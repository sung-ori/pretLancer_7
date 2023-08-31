package com.team.pretLancer_7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    // practice.git
    @GetMapping({" ", "/"})
    public String mainForm() {
        // 마감기한(번역을 수주한 경우), 회원등급, 신뢰도, 보유포인트 불러오는 메서드 필요. 
        return "main";
    }
    @GetMapping("public")
    public String pubilcForm() {
        return "public";
    }
    
    
}
