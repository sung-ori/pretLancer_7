package com.team.pretLancer_7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.service.LongService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/long")
public class LongTranslateController {
    
    @Autowired
    LongService service;

    @GetMapping("/main")
    public String mainForm(){
        return "translate_long/mainForm";
    }

    @GetMapping("/request")
    public String requestForm(Model model) {
        List<Member> translatorList = service.getTranslatorList();
        model.addAttribute("translatorList", translatorList);
        
        return "translate_long/requestForm";
    }
}
