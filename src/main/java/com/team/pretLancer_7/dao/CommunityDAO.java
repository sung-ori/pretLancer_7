package com.team.pretLancer_7.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Board;

@Mapper
public interface CommunityDAO {
    
    public List<Board> selectAll();
}
