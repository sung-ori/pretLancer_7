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
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.pretLancer_7.domain.Exam;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.service.ExamService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("translated")
public class examController {

	@Autowired
	ExamService service;
	
	@GetMapping("tutorial")
	public String tutorial (@AuthenticationPrincipal UserDetails user, Exam ex, Model m) {
		ex.setMemberid(user.getUsername());
		Member member = service.getMemberOne(ex.getMemberid());
		ex.setLanguage(member.getMemberlang());
		log.error("ex 객체 {}", ex);
		Exam question = service.getQuestion(ex);
		log.error("question 객체 {}", question);
		log.error("member 객체 {}", member);
		m.addAttribute("question", question);
		m.addAttribute("member", member);
		return "examForm/tutorial";
	}
	
	
	@PostMapping("tutorial")
	public String tutorialAnswer (@AuthenticationPrincipal UserDetails user, Exam ex, Model m) {
		// Exam ex에 my_answer와 examnum의 값을 HTML에서 받음
		ex.setMemberid(user.getUsername());
		log.error("post로 가져오는 Exam 객체 {}", ex);
		String answer;
		int cnt = service.getAnswer(ex);
		if (cnt == 1) {
			answer = "correct";
		}
		else answer = "failed";
		log.error("cnt : {}", cnt);
		log.error("answer : {}", answer);
		m.addAttribute("answer", answer);

		// tutorial 오른 횟수를 확인
		Member member = service.getMemberOne(ex.getMemberid());
		m.addAttribute("member", member);
		return "redirect:/translated/tutorial";
	}
	
	
	@GetMapping("exam")
	public String exam (@AuthenticationPrincipal UserDetails user, Exam ex, Model m) {
		ex.setMemberid(user.getUsername());
		Member member = service.getMemberOne(ex.getMemberid());
		ex.setLanguage(member.getMemberlang());
		Exam question = service.getQuestion(ex);
		m.addAttribute("question", question);
		m.addAttribute("member", member);
		return "examForm/exam";
	}
	
	@ResponseBody
	@PostMapping("answer_check")
	public String answer_check(int examnum, String memberid) {
		Exam ex = new Exam();
		ex.setMemberid(memberid);
		ex.setExamnum(examnum - 1);
		
		int cnt = service.getExamInfo(ex);
		if (cnt == 1) {
			return "맞았습니다!";
		} else
			return "틀렸습니다.";
	}
}
