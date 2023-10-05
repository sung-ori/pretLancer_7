package com.team.pretLancer_7.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;

@Mapper
public interface TranslatedDAO {

	void insertTS(Request_S rs);

	void insertTM(Request_M rm);

	Translated_S selectTS(int requestnum_s);
	Translated_M selectTM(int requestnum_m);
	
	void submitTS(Translated_S ts);

	void submitTM(Translated_M tm);

	void cancelT(String username);

	void cancelTS(Translated_S ts);

	void cancelTM(Translated_M tm);

	Translated_S getMyTS(Request_S myorderS);
	Translated_M getMyTM(Request_M myorderM);

	List<Translated_S> tsList(String username);
	List<Translated_M> tmList(String username);
	
	// 마이페이지 - 내가 요청한 번역보기 페이지
	Translated_S selectMyPageTS(Translated_S ts);
	Translated_M selectMyPageTM(Translated_M tm);
	// 마이페이지 - 내가 번역한 번역보기 페이지
	Translated_S getMyTransTS(Translated_S ts);
	Translated_M getMyTransTM(Translated_M tm);
	
}
