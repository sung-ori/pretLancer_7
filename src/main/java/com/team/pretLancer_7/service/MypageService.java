package com.team.pretLancer_7.service;

import com.team.pretLancer_7.domain.Ability;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.MyPage;

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

}
