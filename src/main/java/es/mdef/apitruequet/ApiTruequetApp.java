package es.mdef.apitruequet;

import org.slf4j.Logger;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApiTruequetApp {
	public static final Logger log = LoggerFactory.getLogger(ApiTruequetApp.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ApiTruequetApp.class, args);
	}
	
}
