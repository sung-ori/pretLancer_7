package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation_M {

	int evaluationnum_m;
	int translatednum_m;
	int requestnum_m;
	String memberid;
	String evaluationsuccess;
	String whyfailed;
	
}
