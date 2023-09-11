package com.team.pretLancer_7.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
import com.team.pretLancer_7.utill.PageNavigator;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("community")
public class CommunityController {
    
	@Autowired
	CommunityService service;

	// 게시판 목록의 페이지당 글 수 
	@Value("${user.board.page}")
	int countPerPage;
	//게시판 목록의 페이지 이동 링크 수
	@Value("${user.board.group}")
	int pagePerGroup;
	
	// 커뮤니티 게시판 리스트
    @GetMapping("main")
    public String commyMain(Model m,String type, String searchWord,
							@RequestParam(name="page",defaultValue="1") int page) {
    	// 모든 게시물 목록 가져옴
		// 복잡한 코드는 서비스에 넘겨서 받는다.
		PageNavigator navi = service.getPageNavigator(pagePerGroup, countPerPage, page, type, searchWord);
		
		List<Board> list = service.boardList(navi, type, searchWord); 
    	
    	m.addAttribute("list", list);
		m.addAttribute("navi", navi);
		m.addAttribute("type", type);
		m.addAttribute("searchWord", searchWord);
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
		b.setMemberid(user.getUsername());
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

	@GetMapping("policeForm")
	public String policeForm(){
		return "/communityForm/policeForm";
	}

	@ResponseBody
	@GetMapping("policeCheck")
	public String policeCheck(@RequestParam(name="boardnum", defaultValue="0") int boardnum,
							@AuthenticationPrincipal UserDetails user) {
		String id = user.getUsername();
		
		String result = service.policeCheck(boardnum, id);
		return result;
		}
	
	@ResponseBody
	@GetMapping("police")
	public String police(@RequestParam(name="boardnum", defaultValue="0") int boardnum,
							@AuthenticationPrincipal UserDetails user, String reason){
		String id = user.getUsername();
		log.error("신고 컨 {}",reason);
		String msg = service.police(boardnum,id,reason);
		
		
		return msg;
	}

	@ResponseBody
	@GetMapping("policeCount")
	public String policeCount(@RequestParam(name="boardnum", defaultValue="0") int boardnum) {
		return service.policeCount(boardnum);
	}
	
}
