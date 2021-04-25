package com.meb.repo.csv2mongo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import com.meb.repo.csv2mongo.document.Citizens;
import com.opencsv.CSVWriter;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;

public class CSVReadWrite {

	public static List<Citizens> parseCsvFile(InputStream is) {
		String[] CSV_HEADER = { "id", "name", "address", "age" };
		Reader fileReader = null;
		CsvToBean<Citizens> csvToBean = null;
	
		List<Citizens> citizens = new ArrayList<Citizens>();
		
		try {
			fileReader = new InputStreamReader(is);
	
			ColumnPositionMappingStrategy<Citizens> mappingStrategy = new ColumnPositionMappingStrategy<Citizens>();
	
			mappingStrategy.setType(Citizens.class);
			mappingStrategy.setColumnMapping(CSV_HEADER);
	
			csvToBean = new CsvToBeanBuilder<Citizens>(fileReader).withMappingStrategy(mappingStrategy).withSkipLines(1)
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

	public static void customersToCsv(Writer writer, List<Citizens> customers) {
		String[] CSV_HEADER = { "id", "name", "address", "age" };
	    
	    StatefulBeanToCsv<Citizens> beanToCsv = null;
	 
	    try {
	      // write List of Objects
	      ColumnPositionMappingStrategy<Citizens> mappingStrategy = 
	                new ColumnPositionMappingStrategy<Citizens>();
	      
	      mappingStrategy.setType(Citizens.class);
	      mappingStrategy.setColumnMapping(CSV_HEADER);
	      
	      beanToCsv = new StatefulBeanToCsvBuilder<Citizens>(writer)
	          .withMappingStrategy(mappingStrategy)
	                    .withQuotechar(CSVWriter.NO_QUOTE_CHARACTER)
	                    .build();
	 
	      beanToCsv.write(customers);
	    } catch (Exception e) {
	      System.out.println("Writing CSV error!");
	      e.printStackTrace();
	    }
	}
}