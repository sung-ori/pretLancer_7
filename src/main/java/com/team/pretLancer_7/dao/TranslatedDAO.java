package com.team.pretLancer_7.dao;

import org.apache.ibatis.annotations.Mapper;

import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;

@Mapper
public interface TranslatedDAO {

	void insetTS(Translated_S ts);

	void insetTM(Translated_M tm);

}
