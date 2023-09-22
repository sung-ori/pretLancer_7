package com.team.pretLancer_7.reward;

import com.team.pretLancer_7.domain.Reward;

public interface RewardService {
    public void charge(Reward reward);

    public void payback(String userid, int cash);
}
