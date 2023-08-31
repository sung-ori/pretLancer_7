package com.team.pretLancer_7.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.CommunityDAO;
import com.team.pretLancer_7.domain.Board;
import com.team.pretLancer_7.domain.Reply;

@Service
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
    
	
	
}
