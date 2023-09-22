package com.team.pretLancer_7.reward;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.domain.Reward;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
public class RewardServiceImpl implements RewardService {

    @Autowired
    RewardDAO dao;

    @Override
    public void charge(Reward reward) {
        dao.insertPretPay(reward);
        dao.updateCharge(reward);
    }

    @Override
    public void payback(String userid, int cash) {
        Map<String,String> map = new HashMap();
        String realcash = "" + cash;

        map.put("userid", userid);
        map.put("cash", realcash);
        log.debug("그래서 얼마인데? {}", realcash );
        dao.updatePayback(map);
    }
   
    
}

