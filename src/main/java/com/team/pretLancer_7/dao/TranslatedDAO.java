package com.team.pretLancer_7.dao;

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

	void cancelTS(Translated_M tm);
	
}
