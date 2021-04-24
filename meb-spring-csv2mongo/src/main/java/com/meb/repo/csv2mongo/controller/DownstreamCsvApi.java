package com.meb.repo.csv2mongo.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.meb.repo.csv2mongo.service.CsvFileServices;


@RestController
public class DownstreamCsvApi {
	
	@Autowired
	CsvFileServices csvFileService;
	
	//GET method for download
	
	@GetMapping("/clients/downstream")
	public void downloadFile(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=clients.csv");
		csvFileService.getFromDB2File(response.getWriter());
	}
}