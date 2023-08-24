package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	String memberid;
	String memberpw;
	String membername;
	String membermail;
	String membernick;
	int mem_ex;
	String mem_level;
	int enabled;
	String role_name;
	String role_scale;
	int point;
	int cash;
	String tutorial;
	int tutorial_num;
	String memberlang;
}
