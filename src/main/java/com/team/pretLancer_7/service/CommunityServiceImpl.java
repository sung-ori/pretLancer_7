package com.team.pretLancer_7.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.CommunityDAO;
import com.team.pretLancer_7.domain.Board;
import com.team.pretLancer_7.domain.Reply;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CommunityServiceImpl implements CommunityService {

    @Autowired
    CommunityDAO dao;

    @Override
    public List<Board> boardList() {
        return dao.selectAll();
    }

	@Override
	public Board boardListOne(int boardnum) {
		return dao.selectOne(boardnum);
	}

	@Override
	public List<Reply> replyList(int boardnum) {
		return dao.replyAll(boardnum);
	}

	@Override
	public int commyInsert(Board b) {
		return dao.insertOne(b);
	}

	@Override
	public void writeReply(Reply r) {
		dao.insertReply(r);	
	}

	@Override
	public List<Reply> getReplylist(int boardnum) {
		return dao.replyAll(boardnum);
	}

	@Override
	public void deleteReply(Reply r) {
		dao.deleteReply(r);
	}

	@Override
	public int commyDelete(Board b) {
		return dao.deleteOne(b);
	}

	@Override
	public int update(Board b) {
		return dao.updateBoard(b);
		
	}

	@Override
	public int hitsUP(int boardNum) {
		return dao.updateHits(boardNum);
	}

	@Override
	public void recommendUp(int boardNum, String id) {
		
		String bdn = "" + boardNum;
		HashMap<String, String> map = new HashMap<String,String>();

		map.put("boardnum", bdn);
		map.put("memberid", id);
		
		Integer recoRst = dao.selectReco(map);
		Integer decoRst = dao.selectDeco(map);

		log.debug("레코 {}",recoRst);
		log.debug("데코 {}",decoRst);
		
		int rst = 0;

		if (recoRst == null && decoRst == null) {
			rst  = 1;
		}
		else if (recoRst != null && decoRst == null) {
			rst = 2;}
		else if (recoRst != null && decoRst == null) {
			rst = 3;
		}
		log.debug("추천 {}", rst);
		switch (rst) {
			case 1 :
				dao.insertReco(map);
				dao.upReco(boardNum);
				break;
			case 2 :
				dao.deleteReco(map);
				dao.downReco(boardNum);
				break;
			default : break;
				
		}
		
			
			
		}
		
		
	

	@Override
	public void decommendUp(int boardNum, String id) {

		String bdn = "" + boardNum;
		HashMap<String, String> map = new HashMap<String,String>();

		map.put("boardnum", bdn);
		map.put("memberid", id);
		
		Integer recoRst = dao.selectReco(map);
		Integer decoRst = dao.selectDeco(map);

		int rst = 0;

		if (recoRst == null && decoRst == null) {
			rst  = 1;
		}
		else if (recoRst == null && decoRst != null) {
			rst = 2;}
		else if (recoRst != null && decoRst == null) {
			rst = 3;
		}
		log.debug("반대 {}", rst);
		switch (rst) {
			case 1 :
				dao.insertDeco(map);
				dao.upDeco(boardNum);
				break;
			case 2 :
				dao.deleteDeco(map);
				dao.downDeco(boardNum);
				break;
			default : break;
				
		}	
	}

	@Override
	public String colorize(int boardnum, String id) {
		String bdn = "" + boardnum;
		HashMap<String, String> map = new HashMap<String,String>();

		map.put("boardnum", bdn);
		map.put("memberid", id);

		Integer recoRst = dao.selectReco(map);
		Integer decoRst = dao.selectDeco(map);
		int rst = 0;
		
		if (recoRst == null && decoRst == null) {
			rst  = 1;
		}
		else if (recoRst == null && decoRst != null) {
			rst = 2;}
		else if (recoRst != null && decoRst == null) {
			rst = 3;
		}

		String bt = "";
		switch (rst) {
			case 1 : bt = "" ; break;
			case 2 : bt = "decoBt"; break;
			case 3 : bt = "recoBt"; break;
			default : break;
		}

		return bt;
		
	}

	@Override
	public void replyRecommend(int replynum, String id) {
	String rpn = "" + replynum;
	HashMap<String, String> map = new HashMap<String,String>();

	map.put("replynum", rpn);
	map.put("memberid",id);

	Integer rst = dao.selectReplyReco(map);

	if(rst == null) {
		dao.insertReplyReco(map);
		dao.upReplyReco(replynum);
	}
	else{
		dao.deleteReplyReco(map);
		dao.downReplyReco(replynum);
		return;
	}

	}

	@Override
	public String policeCheck(int boardnum, String id) {
		String bdn = "" + boardnum;
		String result = "";
		HashMap<String, String> map = new HashMap<String,String>();

		map.put("boardnum", bdn);
		map.put("memberid", id);

		Integer rst = dao.selectPolice(map);

		log.error("신고 확인 {}",rst);

		if(rst == null){
			result = "true";
		}
		else{result = "false";}

		return result;
		
	}

	@Override
	public String police(int boardnum, String id, String reason) {
		// String msg = " ";
		Board bd =  dao.selectOne(boardnum);
		String writerId = "" ;
		try{
			writerId = bd.getMemberid();
		}catch(NullPointerException e){
			
		}
		log.error("writerId {}",writerId);
		
		String bdn = "" + boardnum;
		String result = "";
		HashMap<String, String> map = new HashMap<String,String>();

		map.put("boardnum", bdn);
		map.put("repoterid", id);

		map.put("writerid", writerId);
		map.put("reason",reason);
		log.error("이유 {}", reason);

		dao.insertPolice(map);
		dao.upPolice(boardnum);

		int cnt = dao.countPolice(boardnum);

		if(cnt >= 5) {
			dao.updateVan(boardnum);
		}

		return "신고가 완료되었습니다.";
	}
	
	
}
