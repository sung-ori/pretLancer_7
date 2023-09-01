package com.team.pretLancer_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.MemberDAO;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.MyPage;

@Service
public class MypageServiceImple implements MypageService {
    
    @Autowired
    MemberDAO mDao;


    @Override
    public MyPage getMyPage(String userId) {
        Member m = mDao.selectOne(userId);

        MyPage mP = mDao.selectOneMyPage(userId);

        mP.setMemberid(m.getMemberid());
        mP.setMembername(m.getMembername());
        mP.setMem_level(m.getMem_level());
        mP.setMembernick(m.getMembernick());
        mP.setMem_ex(m.getMem_ex());
        mP.setOriginphoto(m.getMem_level());
        mP.setMemberlang(m.getMemberlang());
        
        return mP;
    }
}
