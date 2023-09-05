package com.team.pretLancer_7.controller;

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
    	Board board = service.boardListOne(boardnum);
		// 조회수 증가
    	service.hitsUP(boardnum);
    	m.addAttribute("board", board);

    	return "/communityForm/readPage";
    }

	@GetMapping("readInfo")
	@ResponseBody
	public Board readInfo(@RequestParam(name="boardnum", defaultValue="0") int boardnum) {

		return service.boardListOne(boardnum);
	}
    
    // 커뮤니티 게시판 글쓰기 폼
    @GetMapping("write")
    public String commyWrite() {
    	
    	return ("/communityForm/writePage");
    }
    
    // 커뮤니티 게시판 글쓰기 입력
    @PostMapping("write")
    public String commyInsert(@AuthenticationPrincipal UserDetails user, Board b) {
		log.debug("글쓰기 컨트롤러 들어오나? {}", b);
    	b.setMemberid(user.getUsername());
    	
    	int cnt = service.commyInsert(b);
    	return ("redirect:/community/main");
    }
    
    // 글 삭제
    @GetMapping("delete")
    public String commyDelete(@AuthenticationPrincipal UserDetails user, Board b) {
    	b.setMemberid(user.getUsername());
    	
    	int cnt = service.commyDelete(b);
    	return ("redirect:/community/main");
    }

	// 글 수정 폼
	@GetMapping("updateForm")
	public String updateForm(Model m, @RequestParam(name="boardnum", defaultValue="0") int boardnum) {
	Board board = service.boardListOne(boardnum);
    	List<Reply> reply = service.replyList(boardnum);
    	m.addAttribute("board", board);
		return "/communityForm/updatePage";
	}

	//  글 수정
	@PostMapping("update")
	public String update(@AuthenticationPrincipal UserDetails user, Board b) {
		service.update(b);
		return "redirect:/community/main";
	}
    
    // 리플 저장 기능
 	@ResponseBody
 	@PostMapping("insertReply")
 	public void insertReply(@AuthenticationPrincipal UserDetails user, Reply r) {
		log.debug("리플삽입 {}",r);
		r.setMemberid(user.getUsername());
		log.debug("멤버이름 넣고 {}",r);
 		service.writeReply(r);
 	}
 	
 	// 리플 읽어오기 기능
 	@ResponseBody
 	@GetMapping("readReply")
 	public List<Reply> readReply(@RequestParam(name="boardnum", defaultValue="0") int boardnum) {
 		List<Reply> replyList = service.getReplylist(boardnum);
 		return replyList;
 	}
 	
 	// 리플 삭제
 	@ResponseBody
 	@GetMapping("deleteReply")
 	public void deleteReply(@AuthenticationPrincipal UserDetails user ,Reply r) {
 		r.setMemberid(user.getUsername());
 		service.deleteReply(r);
 	}

	// 글 추천
	@ResponseBody
	@GetMapping("recommendUp")
	public void recommendUp(@RequestParam(name="boardnum", defaultValue="0") int boardnum,
							 @AuthenticationPrincipal UserDetails user) {
		String id = user.getUsername();
		service.recommendUp(boardnum,id);
	}

	@ResponseBody
	@GetMapping("decommendUp")
	public void decommendUp(@RequestParam(name="boardnum", defaultValue="0") int boardnum,
							 @AuthenticationPrincipal UserDetails user) {
		String id = user.getUsername();
		service.decommendUp(boardnum,id);
	}
 	
	@ResponseBody
	@GetMapping("colorize")
	public String colorize(@RequestParam(name="boardnum", defaultValue="0") int boardnum,
							@AuthenticationPrincipal UserDetails user){
		String id = user.getUsername();
		return service.colorize(boardnum,id);	
	}

	@ResponseBody
	@GetMapping("replyRecommend")
	public void replyRecommend(@RequestParam(name="replynum", defaultValue="0") int replynum,
							@AuthenticationPrincipal UserDetails user) {
		log.debug("댓글추천 {}",replynum);
		String id = user.getUsername();
		service.replyRecommend(replynum,id );
		
	}
}
