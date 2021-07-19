package com.app.dca.repository;

import java.util.List;

import com.app.dca.entity.Developer;
import com.app.dca.exception.UnknownDeveloperException;

public interface IDeveloperRepository {
	

		Developer saveDeveloper(Developer dev);
		
		Developer updateDeveloper(Developer dev);
		
		Developer statusUpdate(Developer dev);
		
		Developer fetchDeveloper(int devId) throws UnknownDeveloperException;
		
		List<Developer> fetchAllDevelopers();
}
