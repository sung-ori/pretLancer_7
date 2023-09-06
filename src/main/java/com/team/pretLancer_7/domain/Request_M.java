package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request_M {
	int requestnum_m;
	String memberid;
	String memberid2;
	String requestcontent_m;
	String translatedcontent_m;
	String requestcondition_m;
	String inputdate;
	String enddate;
	int cash;
	String comment_mr;
	String startlang;
	String endlang;
}
