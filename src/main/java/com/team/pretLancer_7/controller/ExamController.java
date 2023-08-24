package com.team.pretLancer_7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pretLancer")
public class ExamController {

	@GetMapping("exam")
	public String exam () {
		
		
		return "testpage";
	}
}
