package com.team.pretLancer_7.dao;

import java.util.List;

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

	List<Request_S> rsList(String username);
	List<Request_M> rmList(String username);

	List<Request_S> selectRequestList_S(String userid);
	List<Request_M> selectRequestList_M(String userid);

	Request_S selectOneRequest_S(int requestnum_s);
	Request_M selectOneRequest_M(int requestnum_m);

	List<Request_S> rsList2(String username);
	List<Request_M> rmList2(String username);

	//동일한 translatednum을 사용하는 리퀘스트를 가져옴 (리퀘스트에서 코멘트를 가져오기 위함임)
	
}
