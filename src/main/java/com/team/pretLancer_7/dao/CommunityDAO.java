package com.team.pretLancer_7.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Board;
import com.team.pretLancer_7.domain.Reply;

@Mapper
public interface CommunityDAO {
    
	// 게시판목록
    public List<Board> selectAll();

    // 긁읽기
	public Board selectOne(int Boardnum);
	
	// 댓글목록
	public List<Reply> replyAll(int boardnum);

	// 글쓰기
	public int insertOne(Board b);
	
	// 댓글쓰기
	public void insertReply(Reply r);
	
}
