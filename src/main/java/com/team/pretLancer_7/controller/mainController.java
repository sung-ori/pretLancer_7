package com.team.pretLancer_7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    // practice.git
    @GetMapping({"/"})
    public String mainForm() {
        return "main";
    }
    
    
}
