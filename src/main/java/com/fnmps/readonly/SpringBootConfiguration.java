package com.fnmps.readonly;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@EnableMongoRepositories(basePackages = "com.fnmps.readonly.repositories")
@Configuration
public class SpringBootConfiguration {
	
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@PostConstruct
	public void configureObjectMapper() {
		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
		objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);

	}

}
