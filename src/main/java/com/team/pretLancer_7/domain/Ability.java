package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ability {

	String memberid;
	
	int psucceed;
	int pfailed;
	
	int ssucceed;
	int sfailed;
	
	int msucceed;
	int mfailed;
	
	int lsucceed;
	int lfailed;
	
	int esucceed;
	int efailed;
	
}
