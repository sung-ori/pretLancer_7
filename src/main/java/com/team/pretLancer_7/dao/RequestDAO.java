package com.team.pretLancer_7.dao;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;

@Mapper
public interface RequestDAO {

	void insertRS(Request_S r);

	void cancelRS(Request_S r);

	Request_S selectRS(Request_S r);
	

	void insertRM(Request_M r);

	void cancelRM(Request_M r);

	Request_S selectRM(Request_M r);
	

	Request_S choiceRS();
	
	Request_M choiceRM();

	
	void updateMem2_S(Request_S rs);
	
	void updateMem2_M(Request_M rm);
	
	
	void updateCon_S(Translated_S ts);
	
	void updateCon_M(Translated_M tm);

	
	void cancelCon_S(Translated_S ts);

	void cancelCon_M(Translated_M tm);

	
	Request_S myorderS(String username);

	Request_M myorderM(String username);

	
	Request_S getRequestS(int requestnum_s);
	
	Request_M getRequestM(int requestnum_m);
	
	// 메세징
	Request_S SRS(int r);

	Request_M SRM(int r);

	int RScount();
	int RMcount();
	int TScount();
	int TMcount();
	int EScount();
	int EMcount();
	
}
