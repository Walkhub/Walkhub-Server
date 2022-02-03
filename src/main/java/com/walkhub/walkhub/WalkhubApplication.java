package com.walkhub.walkhub;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableJpaAuditing
@EnableBatchProcessing
@ConfigurationPropertiesScan
@SpringBootApplication
@EnableScheduling
public class WalkhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalkhubApplication.class, args);
	}

}
