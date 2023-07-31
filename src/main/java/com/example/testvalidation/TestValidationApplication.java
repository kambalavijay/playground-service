package com.example.testvalidation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.google.gson.Gson;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import java.lang.reflect.Field;
import java.util.Map;

@SpringBootApplication
public class TestValidationApplication {

	@Autowired
	private UserRepository userRepository;

	// <socialSecurity source="file"><number>666889020</number></socialSecurity>
	// <accountNumber>453620208545</accountNumber>
	public static void main(String[] args) throws JsonProcessingException {
		SpringApplication.run(TestValidationApplication.class, args);
	}
}
