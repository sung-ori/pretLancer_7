package com.team.pretLancer_7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {
    // practice.git
    //iklasdjf;lkadsf;lkasdf
    //adsjfl'oasdjf;lkasdjfk;la./ads;lkfjads;lfk//adsfasd///aksdlfj
    //as;dklja;dlskj;lkasdfkl;adjsf;
    @GetMapping({" ", "/"})
    public String mainForm() {
        return "main";
    }
    
    
}
