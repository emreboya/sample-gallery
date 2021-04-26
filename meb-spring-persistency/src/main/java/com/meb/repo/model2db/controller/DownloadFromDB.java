package com.meb.repo.model2db.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.meb.repo.model2db.service.Csv2DatabaseService;

// rest interface shit

@RestController
public class DownloadFromDB {
	
	@Autowired
	Csv2DatabaseService csvFileService;
	
	@GetMapping("/clients/downstream")
	public void downloadFile(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		response.setHeader("Content-Disposition", "attachment; filename=clients.csv");
		csvFileService.getFromDB2File(response.getWriter());
	}

}
