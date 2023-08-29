package com.team.pretLancer_7.service;

import java.util.ArrayList;
import java.util.List;

import com.team.pretLancer_7.domain.Board;
import com.team.pretLancer_7.domain.Reply;

public interface CommunityService {
    
    public List<Board> boardList() ;

	public Board boardListOne(int boardnum);

	public List<Reply> replyList(int boardnum);

	public int commyInsert(Board b);

	public void writeReply(Reply r);

	public List<Reply> getReplylist(int boardnum);


}
