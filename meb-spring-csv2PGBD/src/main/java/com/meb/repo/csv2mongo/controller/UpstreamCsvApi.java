package com.meb.repo.csv2mongo.controller;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.meb.repo.csv2mongo.message.Message;
import com.meb.repo.csv2mongo.message.Response;
import com.meb.repo.csv2mongo.service.CsvFileServices;
import com.meb.repo.csv2mongo.utils.ApacheCSVUtilities;


@RestController
@RequestMapping("/clients/upstream")
public class UpstreamCsvApi {
	@Autowired
	CsvFileServices csvFileServices;

	@PostMapping("/multiple")
	public Response uploadMultipleFiles(@RequestParam("multipleFiles") MultipartFile[] csvfiles) {
	
		Response response = new Response();

		MultipartFile[] readyUploadedFiles = Arrays.stream(csvfiles)
				.filter(x -> !StringUtils.isEmpty(x.getOriginalFilename())).toArray(MultipartFile[]::new);
	

		if (readyUploadedFiles.length == 0) {
			response.addMessage(new Message("", "File Not Available", "failed"));
			return response;
		}
	

		String notCsvFiles = Arrays.stream(csvfiles).filter(x -> !ApacheCSVUtilities.isCSVFile(x))
								 	.map(x -> x.getOriginalFilename()).collect(Collectors.joining(" , "));
	
		if (!StringUtils.isEmpty(notCsvFiles)) {
			response.addMessage(new Message(notCsvFiles, "Unsupported File", "failed"));
			return response;
		}
		 

		for (MultipartFile file : readyUploadedFiles) {
			try {
				csvFileServices.persistDB(file.getInputStream());
				response.addMessage(new Message(file.getOriginalFilename(), "Upload Done", "ok"));
			} catch (Exception e) {
				response.addMessage(new Message(file.getOriginalFilename(), e.getMessage(), "upload failed"));
			}
		}
	
		return response;
	}

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