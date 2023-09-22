package com.team.pretLancer_7.service;

import com.team.pretLancer_7.domain.Request_M;
import com.team.pretLancer_7.domain.Request_S;
import com.team.pretLancer_7.domain.Translated_M;
import com.team.pretLancer_7.domain.Translated_S;

public interface TranslatedService {

	Translated_S insertTS(Request_S rs);

	Translated_M insertTM(Request_M rm);

	void submitTS(Translated_S ts);

	void submitTM(Translated_M tm);

	void cancelTS(Translated_S ts);

	void cancelTM(Translated_M tm);

	Translated_S getMyTS(Request_S myorderS);

	Translated_M getMyTM(Request_M myorderM);
	
}
