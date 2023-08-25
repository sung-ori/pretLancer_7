package com.team.pretLancer_7.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Exam {
	
	String memberid;
	int examnum;
	String exam;
	String exam_answer;
	String language;
	String my_answer;

}
