package com.sbonacho.seda.examples.insurance;

import com.sbonacho.seda.examples.insurance.bus.kafka.listeners.ClientCreatedListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PortfoliosBoot implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(PortfoliosBoot.class);

	@Autowired
	ClientCreatedListener listener;

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(PortfoliosBoot.class);
		app.run(args);
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("Service Listening!");
	}
}
