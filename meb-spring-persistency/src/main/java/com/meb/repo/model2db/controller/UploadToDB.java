package com.meb.repo.model2db.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.meb.repo.model2db.message.Message;
import com.meb.repo.model2db.message.Response;
import com.meb.repo.model2db.service.Csv2DatabaseService;
import com.meb.repo.model2db.utils.ApacheCSVUtilities;

@RestController
@RequestMapping("/clients/upstream")
public class UploadToDB {
	
	@Autowired
	Csv2DatabaseService csvFileServices;
	
	@PostMapping("/single")
	public Response uploadSingleCSVFile(@RequestParam("singleFile") MultipartFile csvfile) {
	
		Response response = new Response();
	
		// Check if filename is ok
		
		if (csvfile.getOriginalFilename().isEmpty()) {
			response.addMessage(new Message(csvfile.getOriginalFilename(),
					"No selected file to upload! Please do the checking", "fail"));
	
			return response;
		}
	
		//file control if CSV is ok
		
		if(!ApacheCSVUtilities.isCSVFile(csvfile)) { 
		    response.addMessage(new Message(csvfile.getOriginalFilename(), "Error: this is not a CSV file!", "fail")); 
	        return response; 
		}
		  
		//insert DB
		
		try {
			csvFileServices.persistDB(csvfile.getInputStream());
			response.addMessage(new Message(csvfile.getOriginalFilename(), "Upload File Successfully!", "ok"));
		} catch (Exception e) {
			response.addMessage(new Message(csvfile.getOriginalFilename(), e.getMessage(), "fail"));
		}
	
		return response;
	}


}
