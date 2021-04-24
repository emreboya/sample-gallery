package com.meb.repo.csv2mongo.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meb.repo.csv2mongo.document.Citizens;
import com.meb.repo.csv2mongo.repository.CitizenRepository;
import com.meb.repo.csv2mongo.utils.ApacheCSVUtilities;


@Service
public class CsvFileServices {
	
	@Autowired
	CitizenRepository customerRepository;

	public void persistDB(InputStream file) {
		try {
			
			List<Citizens> lstCustomers = ApacheCSVUtilities.parseCsvFile(file);
			
			customerRepository.saveAll(lstCustomers);
			
		} catch(Exception e) {
			throw new RuntimeException("FAIL! -> message = " + e.getMessage());
		}
	}

	
    public void getFromDB2File(Writer writer) throws IOException {
    	try {
        	List<Citizens> customers = (List<Citizens>) customerRepository.findAll();
        	

             ApacheCSVUtilities.customersToCsv(writer, customers);
		
    	} catch(Exception e) {
    		throw new RuntimeException("Fail! -> Message = " + e.getMessage());
    	}
    }
}