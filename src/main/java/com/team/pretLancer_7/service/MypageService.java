package com.team.pretLancer_7.service;

import java.util.List;

import com.team.pretLancer_7.domain.Ability;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;

public interface MypageService {
    
    public MyPage getMyPage(String userId) ;

	public int updateProfile(MyPage mp);

	public int updateMember(Member m);

	public int changeNick(Member member);

	public int checkPoint(Member member);

	public int getPper(String username);
	public int getSper(String username);
	public int getMper(String username);
	public int getEper(String username);

	public Ability getAbility(String username);

	public List<Request_S> myRquestList_S(String userid);

	public List<Request_M> myRquestList_M(String userid);

}
