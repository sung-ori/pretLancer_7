package com.team.pretLancer_7.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.authentication.UserServiceBeanDefinitionParser;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.team.pretLancer_7.domain.Exam;
import com.team.pretLancer_7.service.ExamService;

@Controller
@RequestMapping("translated")
public class examController {

	@Autowired
	ExamService service;
	
	@GetMapping("tutorial")
	public String tutorial (@AuthenticationPrincipal UserDetails user, Exam ex, Model m) {
		// Exam ex에 language 값을 HTML에서 받음
		ex.setMemberid(user.getUsername());
		Exam question = service.getQuestion(ex);
		m.addAttribute("question", question);
		return "examForm/tutorial";
	}
	
	@PostMapping("tutorial")
	public String tutorialAnswer (@AuthenticationPrincipal UserDetails user, Exam ex, Model m) {
		// Exam ex에 my_answer와 examnum의 값을 HTML에서 받음
		ex.setMemberid(user.getUsername());
		String answer;
		int cnt = service.getAnswer(ex);
		if (cnt == 1) {
			answer = "correct";
		}
		else answer = "failed";
		 // 정답이 맞았으면 tutorial이 1개 오르고, exam에 memberid와 examnum이 추가되는 식 추가
		m.addAttribute("answer", answer);
		return "examForm/tutorial";
	}
	
	@GetMapping("exam")
	public String exam (@AuthenticationPrincipal UserDetails user, Exam ex, Model m) {
		ex.setMemberid(user.getUsername());
		Exam question = service.getQuestion(ex);
		m.addAttribute("question", question);
		return "examForm/exam";
	}
	
	@GetMapping("exam")
	public String examAnswer () {
		
		return "examForm/exam";
	}
	
	
}
