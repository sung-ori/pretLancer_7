package com.team.pretLancer_7.reward;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Reward;

@Mapper
public interface RewardDAO {
    public int insertPretPay(Reward reward);
    public int updateCharge(Reward reward);
}
