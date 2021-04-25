package com.meb.repo.model2db.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import com.meb.repo.model2db.model.Citizen;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class readWriteUtil {
	
	private static String csvExtension = "csv";
	
	public static List<Citizen> parseCsvFile(InputStream is) {
		String[] CSV_HEADER = { "citizenId", "citizenName", "citizenLastName", "citizenAge" };
		Reader fileReader = null;
		CsvToBean<Citizen> csvToBean = null;
	
		List<Citizen> citizens = new ArrayList<Citizen>();
		
		try {
			fileReader = new InputStreamReader(is);
	
			ColumnPositionMappingStrategy<Citizen> mappingStrategy = new ColumnPositionMappingStrategy<Citizen>();
	
			mappingStrategy.setType(Citizen.class);
			mappingStrategy.setColumnMapping(CSV_HEADER);
	
			csvToBean = new CsvToBeanBuilder<Citizen>(fileReader).withMappingStrategy(mappingStrategy).withSkipLines(1)
					.withIgnoreLeadingWhiteSpace(true).build();
	
			citizens = csvToBean.parse();
			
			return citizens;
		} catch (Exception e) {
			System.out.println("Reading CSV Error!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				System.out.println("Closing fileReader/csvParser Error!");
				e.printStackTrace();
			}
		}
		
		return citizens;
	}
	
	public static void customersToCsv(Writer writer, List<Citizen> citizens) throws IOException {

		try (CSVPrinter csvPrinter = new CSVPrinter(writer,
				CSVFormat.DEFAULT.withHeader("citizenID", "citizenName", "citizenLastName", "citizenAge"));) {
			for (Citizen citizen :citizens) {
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
}
