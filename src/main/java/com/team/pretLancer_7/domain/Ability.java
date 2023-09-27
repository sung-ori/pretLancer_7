package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ability {

	String memberid;
	
	// 연습문제 성공, 실패
	int psucceed;
	int pfailed;
	
	// 단문 성공, 실패
	int ssucceed;
	int sfailed;
	
	// 중문 성공, 실패
	int msucceed;
	int mfailed;
	
	// 장문 성공, 실패
	int lsucceed;
	int lfailed;
	
	// 평가 성공, 실패
	int esucceed;
	int efailed;
	
}
