package com.team.pretLancer_7.dao;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;

@Mapper
public interface RequestDAO {

	void insertRS(Request_S r);

	void deleteRS(Request_S r);

	Request_S selectRS(Request_S r);

	void insertRM(Request_M r);

	void deleteRM(Request_M r);

	Request_S selectRM(Request_M r);
	
}
