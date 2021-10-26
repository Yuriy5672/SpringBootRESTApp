package com.app;

import com.app.service.CsvSuggestionConverter;
import com.app.service.CsvSuggestionWriter;
import com.app.service.WebappAPIClient;
import com.google.common.collect.ImmutableList;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import static java.util.stream.Collectors.collectingAndThen;
import static java.util.stream.Collectors.toList;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private CsvSuggestionWriter csvSuggestionWriter;
	@Autowired
	private WebappAPIClient webappAPIClient;
	@Autowired
	private CsvSuggestionConverter csvSuggestionConverter;

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate(new HttpComponentsClientHttpRequestFactory(HttpClientBuilder.create().build()));
	}

	public static void main(String[] args) {
		//Run spring application builder
		new SpringApplicationBuilder(Application.class)
				//Disable Spring Banner
				.bannerMode(Banner.Mode.OFF)
				//Run application
				.run(args);
	}

	//When the method terminates, the application terminates
	@Override
	public void run(String... args) throws Exception {
		//Assigning a value to a variable with trimmed spaces
		String cityName = args[0].trim();
		String fileName = cityName + ".csv";

		//Recording and converting data received from the 'webapp API Client' into a .csv file
		csvSuggestionWriter.write(fileName, webappAPIClient.findSuggestionsByCity(cityName).stream()
				.map(csvSuggestionConverter::toCsvSuggestionDto)
				.collect(collectingAndThen(toList(), ImmutableList::copyOf)));
	}
}
