package com.team.pretLancer_7.service;

import java.util.List;

import com.team.pretLancer_7.domain.Translated_S;
import com.team.pretLancer_7.domain.Ability;
import com.team.pretLancer_7.domain.Evaluation_M;
import com.team.pretLancer_7.domain.Evaluation_S;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;
import com.team.pretLancer_7.domain.Translated_M;

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

	public Request_S readRequestInfo_S(int requestnum_s);

	public Request_M readRequestInfo_M(int requestnum_m);

	public Translated_S getTS(int requestnum_s);
	public Translated_M getTM(int requestnum_m);

	public void changeProfile(MyPage mp);

	public Translated_S getMyPageTS(Translated_S ts);
	public Translated_M getMyPageTM(Translated_M tm);

	public List<Evaluation_S> getWhyRS(int translatednum_s);
	public List<Evaluation_M> getWhyRM(int translatednum_m);

	public Translated_S getMyTransTS(Translated_S ts);
	public Translated_M getMyTransTM(Translated_M tm);
	
}
