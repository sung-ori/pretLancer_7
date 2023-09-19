package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QnA {
    int qnanum;
    String memberid;
    String qnacategory;
    String qnatitle;
    String qnacontent;
    String qnafile;
    String qnaemail;
}
