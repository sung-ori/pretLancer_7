package com.team.pretLancer_7.dao;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;

@Mapper
public interface RequestDAO {

	void insertRS(Request_S r);

	void cancelRS(Request_S r);

	Request_S selectRS(Request_S r);

	void insertRM(Request_M r);

	void cancelRM(Request_M r);

	Request_S selectRM(Request_M r);

	Request_S choiceRS();
	
}
