package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Translated_M {
	
	int translatednum_m;
	int requestnum_m;
	String memberid;
	String requestcontent_m;
	String translatedcontent_m;
	String comment_mt;

}
