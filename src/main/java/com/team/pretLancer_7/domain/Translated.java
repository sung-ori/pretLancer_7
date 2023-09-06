package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Translated {
	
	int translatednum_s;
	int requestnum_s;
	String memberid;
	String requestcontent_s;
	String translatedcontent_s;
	String comment_st;
	
}
