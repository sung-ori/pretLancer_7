package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reply {

	int replynum;
	int boardnum;
	String memberid;
	String replycontent;
	String replydate;
	int reply_recommend;
}
