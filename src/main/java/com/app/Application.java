package com.app;

import com.app.service.CsvSuggestionWriter;
import com.google.common.collect.ImmutableList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private CsvSuggestionWriter csvSuggestionWriter;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	//When the method terminates, the application terminates
	@Override
	public void run(String... args) throws Exception {
		//Assigning a value to a variable with trimmed spaces
		String cityName = args[0].trim();
		String fileName = cityName + ".csv";

		//Recording and converting data received from the 'webapp API Client' into a .csv file
		csvSuggestionWriter.write(fileName, webappAPIClient.findSuggestionsByCity().stream()
				.map(csvSuggestionConverter::toCsvSuggestionDto)
				.collect(collectingAndThen(toList(), ImmutableList::copyOf)));
	}
}
