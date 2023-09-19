package com.team.pretLancer_7.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("evaluation")
public class EvaluationController {

	@GetMapping("main")
	public String evalhome () {
		
		return "evaluationForm/EselectPage";
	}
	
	@GetMapping("getES")
	public String getES() {
		
		return "evaluationForm/evaluationPageS";
	}
	
	@GetMapping("getEM")
	public String getEM() {
		
		return "evaluationForm/evaluationPageM";
	}
	
	@PostMapping("insertES")
	public String insertES() {
		
		return "redirect:/";
	}
	
	@PostMapping("insertEM")
	public String insertEM() {
		
		return "redirect:/";
	}
	
}
