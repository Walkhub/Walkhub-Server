package com.walkhub.walkhub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@ConfigurationPropertiesScan
@SpringBootApplication
public class WalkhubApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalkhubApplication.class, args);
	}

}
