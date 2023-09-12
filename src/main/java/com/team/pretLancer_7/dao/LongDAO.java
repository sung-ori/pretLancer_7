package com.team.pretLancer_7.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.domain.Request_L;

@Mapper
public interface LongDAO {
    public List<MyPage> selectAdTranslator();

    public MyPage selectOneMyPage(String memberid);

    public int insertOneRequst(Request_L request_L);
}
