package com.newsfeedback.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author 729706
 * Name: Ankur Bose
 * Date: Feb 28, 2019
 */
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	private static final Logger logger=LoggerFactory.getLogger(Application.class);
	public static void main(String args[]) {
		logger.info("Booting Started");
		SpringApplication.run(Application.class, args);
		logger.info("Boot Completed");
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.info("Building Started");
		return application.sources(Application.class);
	}
}
