package com.team.pretLancer_7.service;

import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.MyPage;

public interface MypageService {
    
    public MyPage getMyPage(String userId) ;

	public int updateProfile(MyPage mp);

	public int updateMember(Member m);

}
