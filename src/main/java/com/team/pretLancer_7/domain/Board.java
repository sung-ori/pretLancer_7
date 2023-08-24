package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Board {
    int boardnum;
    String memeberid;
    String boardcontent;
    String inpudate;
    int hits;
    int recommend;
    int decommend;
    int police;
}