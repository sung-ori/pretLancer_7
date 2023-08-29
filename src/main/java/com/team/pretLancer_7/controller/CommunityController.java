package com.team.pretLancer_7.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.team.pretLancer_7.domain.Board;
import com.team.pretLancer_7.domain.Reply;
import com.team.pretLancer_7.service.CommunityService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("community")
public class CommunityController {
    
	@Autowired
	CommunityService service;
	
	// 커뮤니티 게시판 리스트
    @GetMapping("main")
    public String commyMain(Model m) {
    	// 모든 게시물 목록 가져옴
    	List<Board> list = service.boardList();

    	m.addAttribute("list", list);
		return ("/communityForm/mainPage");
    }
    
    // 커뮤니티 게시판 글읽기 + 댓글읽기
    @GetMapping("read")
    public String commyRead(Model m, @RequestParam(name="boardnum", defaultValue="0") int boardnum) {
    	Board list = service.boardListOne(boardnum);
    	List<Reply> reply = service.replyList(boardnum);
    	m.addAttribute("list", list);
    	m.addAttribute("reply", reply);
    	return ("/communityForm/readPage");
    }
    
    // 커뮤니티 게시판 글쓰기 폼
    @GetMapping("write")
    public String commyWrite() {
    	
    	return ("/communityForm/writePage");
    }
    
    // 커뮤니티 게시판 글쓰기 입력
    @PostMapping("write")
    public String commyInsert(@AuthenticationPrincipal UserDetails user, Board b) {
    	b.setMemeberid(user.getUsername());
    	
    	int cnt = service.commyInsert(b);
    	return ("redirect:/community/main");
    }
    
    // 리플 저장 기능
 	@ResponseBody
 	@PostMapping("insertReply")
 	public void insertReply(@AuthenticationPrincipal UserDetails user, Reply r) {
 		r.setMemeberid(user.getUsername());
 		service.writeReply(r);
 	}
 	
 	// 리플 읽어오기 기능
 	@ResponseBody
 	@GetMapping("readReply")
 	public List<Reply> readReply(int boardnum) {
 		List<Reply> replyList = service.getReplylist(boardnum);
 		return replyList;
 	}
 	
}
