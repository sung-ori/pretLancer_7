package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuctionTranslator {
    
    int auctionnum;
    String memberid ;
    String mem_level;
    String translatervalue;
    String comment;
}
