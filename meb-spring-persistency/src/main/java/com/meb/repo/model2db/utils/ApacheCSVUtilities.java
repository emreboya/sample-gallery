package com.meb.repo.model2db.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.meb.repo.model2db.model.Citizen;

public class ApacheCSVUtilities {
	private static String csvExtension = "csv";
	
	public static void customersToCsv(Writer writer, List<Citizen> citizens) throws IOException {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.DEFAULT.withHeader("citizenID", "customerName", "customerSurname", "age"));) {
			for (Citizen citizen : citizens) {
				List<String> data = Arrays.asList(String.valueOf(citizen.getCitizenId()), citizen.getCitizenName(),
						citizen.getCitizenLastName(), String.valueOf(citizen.getAge()));

				csvPrinter.printRecord(data);
			}
			csvPrinter.flush();
		} catch (Exception e) {
			System.out.println("Writing CSV error!");
			e.printStackTrace();
		}
	}

	public static List<Citizen> parseCsvFile(InputStream is) {
		BufferedReader fileReader = null;
		CSVParser csvParser = null;

		List<Citizen> citizens = new ArrayList<Citizen>();

		try {
			fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			csvParser = new CSVParser(fileReader,
					CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Citizen citizen = new Citizen(Long.parseLong(csvRecord.get("citizenID")),csvRecord.get("citizenName"),
						csvRecord.get("citizenSurname"), Integer.parseInt(csvRecord.get("age")));

				citizens.add(citizen);
			}

		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvParser.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader/csvParser Error!");
				e.printStackTrace();
			}
		}

		return citizens;
	}
	
	public static boolean isCSVFile(MultipartFile file) {
		String extension = file.getOriginalFilename().split("\\.")[1];
		
		if(!extension.equals(csvExtension)) {
			return false;
		}
		
		return true;
	}

}