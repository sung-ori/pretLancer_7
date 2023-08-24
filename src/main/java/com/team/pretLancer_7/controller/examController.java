package com.team.pretLancer_7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("translated")
public class examController {

	@GetMapping("exam")
	public String exam () {
		
		return "examForm/exam";
	}
	
	@GetMapping("exam")
	public String examAnswer () {
		
		return "examForm/exam";
	}
	
	
}
