package com.team.pretLancer_7.service;

import com.team.pretLancer_7.domain.Ability;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.MyPage;

public interface MemberService {

	int insertMember(Member m);

	boolean idcheck(String searchid);

	Member getUser(String searchid);

	void insertMyPage(MyPage mp);

	void insertAbility(Ability ab);

}
