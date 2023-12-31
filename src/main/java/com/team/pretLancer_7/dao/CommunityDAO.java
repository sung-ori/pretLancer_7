package com.team.pretLancer_7.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.team.pretLancer_7.domain.Board;
import com.team.pretLancer_7.domain.Reply;

@Mapper
public interface CommunityDAO {
    
	// 게시판목록
    public List<Board> selectAll(HashMap<String, String> map, RowBounds rb);

	public int countAll(HashMap<String, String> map);
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

	// 추천 확인
	public Integer selectReco(HashMap<String, String> map);
	
	public int insertReco(HashMap<String, String> map);
	
	public int deleteReco(HashMap<String, String> map);
	
	public int upReco(int boardNum);
	
	public int downReco(int boardNum);

	// 반대 확인
	public Integer selectDeco(HashMap<String, String> map);

	public int insertDeco(HashMap<String, String> map);
	
	public int deleteDeco(HashMap<String, String> map);
	
	public int upDeco(int boardNum);
	
	public int downDeco(int boardNum);

	// 댓글 확인
	public Integer selectReplyReco(HashMap<String, String> map);

	public int insertReplyReco(HashMap<String, String> map);
	
	public int deleteReplyReco(HashMap<String, String> map);

	public int upReplyReco(int replynum);

	public int downReplyReco(int replynum);

	// 신고 확인
	public Integer selectPolice(HashMap<String, String> map);

	public int insertPolice(HashMap<String, String> map);

	public int countPolice(int boardnum);

	public int upPolice(int boardnum);

	public int updateVan(int boardnum);

	public List<Board> todayPopular();

	// 포인트 획득
	public void ReplyPoint(String username);
	public void WritePoint(String username);
}
