package com.meb.repo.model2db.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meb.repo.model2db.model.Citizen;
import com.meb.repo.model2db.repository.CitizenRepository;
import com.meb.repo.model2db.utils.ApacheCSVUtilities;

@Service

public class Csv2DatabaseService {
	
	@Autowired
	CitizenRepository citizenRepository;
	
	public void persistDB(InputStream file) {
		try  {
			List<Citizen> lstCitizens = ApacheCSVUtilities.parseCsvFile(file);
			
			citizenRepository.saveAll(lstCitizens);
			
		} catch (Exception e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}
	
	public void getFromDB2File(Writer writer) throws IOException {
    	try {
        	List<Citizen> citizens = (List<Citizen>) citizenRepository.findAll();
        	

             ApacheCSVUtilities.customersToCsv(writer, citizens);
		
    	} catch(Exception e) {
    		throw new RuntimeException("Fail! -> Message = " + e.getMessage());
    	}
	}

}
