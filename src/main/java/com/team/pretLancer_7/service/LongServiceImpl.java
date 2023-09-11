package com.team.pretLancer_7.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.LongDAO;
import com.team.pretLancer_7.domain.MyPage;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LongServiceImpl implements LongService{

    @Autowired
    LongDAO dao ;

    @Override
    public List<MyPage> getTranslatorList() {
        List<MyPage> translatorList = new ArrayList();

        translatorList =  dao.selectAdTranslator();
        log.error("서비스는 돌아오나? {}", translatorList);
        return translatorList;
    }
    
}
