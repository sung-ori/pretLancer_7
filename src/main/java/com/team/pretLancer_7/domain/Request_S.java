package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Request_S {
	int requestnum_s;
	String memberid;
	String memberid2;
	String requestcontent_s;
	String translatedcontent_s;
	String requestcondition_s;
	String inputdate;
	String enddate;
	int cash;
	String comment_sr;
	String startlang;
	String endlang;
}
