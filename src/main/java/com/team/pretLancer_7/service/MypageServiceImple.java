package com.team.pretLancer_7.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.AbilityDAO;
import com.team.pretLancer_7.dao.MemberDAO;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.MyPage;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MypageServiceImple implements MypageService {
    
    @Autowired
    MemberDAO mDao;
    
    @Autowired
    AbilityDAO aDao;


    @Override
    public MyPage getMyPage(String userId) {
        Member m = mDao.selectOne(userId);
        
        MyPage Mp = mDao.selectOneMyPage(userId);

        Mp.setMemberid(m.getMemberid());
        Mp.setMembername(m.getMembername());
        Mp.setMem_level(m.getMem_level());
        Mp.setMembernick(m.getMembernick());
        Mp.setMem_ex(m.getMem_ex());
        
        return Mp;
    }

	@Override
	public int updateProfile(MyPage mp) {
		return mDao.updateProfile(mp);
	}

	@Override
	public int updateMember(Member m) {
		return mDao.updateMember(m);
	}

	@Override
	public int changeNick(Member member) {
		mDao.minusPoint(member);
		return mDao.changeNick(member);
	}

	@Override
	public int checkPoint(Member member) {
		return mDao.checkPoint(member);
	}

	@Override
	public int getPper(String username) {
		return aDao.getPper(username);
	}

	@Override
	public int getSper(String username) {
		return aDao.getSper(username);
	}

	@Override
	public int getMper(String username) {
		return aDao.getMper(username);
	}

	@Override
	public int getEper(String username) {
		return aDao.getEper(username);
	}
    
    
    
}
