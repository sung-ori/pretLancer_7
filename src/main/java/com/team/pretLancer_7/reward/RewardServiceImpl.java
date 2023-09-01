package com.team.pretLancer_7.reward;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team.pretLancer_7.domain.Reward;

@Service
public class RewardServiceImpl implements RewardService {

    @Autowired
    RewardDAO dao;

    @Override
    public void charge(Reward reward) {
        dao.insertPretPay(reward);
        dao.updateCharge(reward);
    }
    
}
