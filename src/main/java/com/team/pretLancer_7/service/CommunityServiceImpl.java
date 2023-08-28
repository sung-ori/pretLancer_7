package com.team.pretLancer_7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.team.pretLancer_7.dao.CommunityDAO;
import com.team.pretLancer_7.domain.Board;

public class CommunityServiceImpl implements CommunityService {

    @Autowired
    CommunityDAO dao;

    @Override
    public List<Board> boardList() {
        return dao.selectAll();
    }
    
}
