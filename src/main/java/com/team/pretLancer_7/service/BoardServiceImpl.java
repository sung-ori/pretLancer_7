package com.team.pretLancer_7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.CommunityDAO;
import com.team.pretLancer_7.domain.Board;


@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    CommunityDAO dao;

    @Override
    public List<Board> boardList() {
        
        return dao.selectAll();
        
    }
    
}
