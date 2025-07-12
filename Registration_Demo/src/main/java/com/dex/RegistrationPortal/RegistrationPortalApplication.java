package com.dex.RegistrationPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.dex")
public class RegistrationPortalApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistrationPortalApplication.class, args);
	}

}
