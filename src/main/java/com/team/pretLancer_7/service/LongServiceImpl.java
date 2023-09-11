package com.team.pretLancer_7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.LongDAO;
import com.team.pretLancer_7.domain.Member;

@Service
public class LongServiceImpl implements LongService{

    @Autowired
    LongDAO dao ;

    @Override
    public List<Member> getTranslatorList() {
       return dao. selectAdTranlator();
    }
    
}
