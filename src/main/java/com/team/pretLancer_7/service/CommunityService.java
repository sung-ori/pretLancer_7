package com.team.pretLancer_7.service;

import java.util.List;

import com.team.pretLancer_7.domain.Board;
import com.team.pretLancer_7.domain.Reply;
import com.team.pretLancer_7.utill.PageNavigator;

public interface CommunityService {
    
    public List<Board> boardList(PageNavigator navi, String type, String searchWord) ;
	
	public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type,
	String searchWord);
	public Board boardListOne(int boardnum);

	public List<Reply> replyList(int boardnum);

	public int commyInsert(Board b);

	public void writeReply(Reply r);

	public List<Reply> getReplylist(int boardnum);

	public void deleteReply(Reply r);

	public int commyDelete(Board b);

	public int update(Board b);

	public int hitsUP(int boardNum);

	public void recommendUp(int boardNum, String id);

	public void decommendUp(int boardNum, String id);

	public String colorize(int boardnum, String id);

	public void replyRecommend(int replynum, String id);

	public String policeCheck(int boardnum, String id);

	public String police(int boardnum, String id, String reason);

	public String policeCount(int boardnum);

	public List<Board> todayPopular();

}
