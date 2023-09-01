package com.team.pretLancer_7.service;

import com.team.pretLancer_7.domain.Member;

public interface MemberService {

	int insertMember(Member m);

	boolean idcheck(String searchid);

	Member getUser(String searchid);

}
