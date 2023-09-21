package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Evaluation_S {

	int evaluationnum_s;
	int translatednum_s;
	int requestnum_s;
	String memberid;
	String evaluationsuccess;
	String whyfailed;
	
}
