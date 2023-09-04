package com.team.pretLancer_7.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Board;
import com.team.pretLancer_7.domain.Reply;

@Mapper
public interface CommunityDAO {
    
	// 게시판목록
    public List<Board> selectAll();

    // 긁읽기
	public Board selectOne(int boardnum);

	// 조회수 상승
	public int updateHits(int boardnum);

	// 댓글목록
	public List<Reply> replyAll(int boardnum);

	// 글쓰기
	public int insertOne(Board b);
	
	// 댓글쓰기
	public void insertReply(Reply r);

	// 댓글 삭제
	public void deleteReply(Reply r);
	
	// 글삭제
	public int deleteOne(Board b);
	
	// 글 수정
	public int updateBoard(Board b);

	// 댓글 확인
	public int selectReco(HashMap<String, String> map);

	public int deleteReco(HashMap<String, String> map);

	public int downReco(int boardNum);
	
	public int upReco(int boardNum);

	public int insertReco(HashMap<String, String> map);
}
