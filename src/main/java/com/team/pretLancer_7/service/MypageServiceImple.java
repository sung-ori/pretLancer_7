package com.team.pretLancer_7.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.dao.AbilityDAO;
import com.team.pretLancer_7.dao.MemberDAO;
import com.team.pretLancer_7.dao.RequestDAO;
import com.team.pretLancer_7.dao.TranslatedDAO;
import com.team.pretLancer_7.domain.Ability;
import com.team.pretLancer_7.domain.Member;
import com.team.pretLancer_7.domain.MyPage;
import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MypageServiceImple implements MypageService {
    
    @Autowired
    MemberDAO mDao;
    
    @Autowired
    AbilityDAO aDao;

    @Autowired
    RequestDAO rDao;
    
    @Autowired
    TranslatedDAO tDao;

    @Override
    public MyPage getMyPage(String userId) {
        Member m = mDao.selectOne(userId);
        
        MyPage Mp = mDao.selectOneMyPage(userId);

        Mp.setMemberid(m.getMemberid());
        Mp.setMembername(m.getMembername());
        Mp.setMem_level(m.getMem_level());
        Mp.setMembernick(m.getMembernick());
        Mp.setMem_ex(m.getMem_ex());
        Mp.setMemberlang(m.getMemberlang());
        
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

	@Override
	public Ability getAbility(String username) {
		return aDao.getAbility(username);
	}

	@Override
	public List<Request_S> myRquestList_S(String userid) {
		return rDao.selectRequestList_S(userid);
	}
    
	@Override
	public List<Request_M> myRquestList_M(String userid) {
		return rDao.selectRequestList_M(userid);
	}

	@Override
	public Request_S readRequestInfo_S(int requestnum_s) {
		return rDao.selectOneRequest_S(requestnum_s);
	}
    
	@Override
	public Request_M readRequestInfo_M(int requestnum_m) {
		return rDao.selectOneRequest_M(requestnum_m);
	}
    
	@Override
	public Translated_S getTS(int requestnum_s) {
		return tDao.selectTS(requestnum_s);
	};

	@Override
	public Translated_M getTM(int requestnum_m) {
		return tDao.selectTM(requestnum_m);
	}

	@Override
	public void changeProfile(MyPage mp) {
		mDao.changeProfile(mp);
	};

	
	
}
