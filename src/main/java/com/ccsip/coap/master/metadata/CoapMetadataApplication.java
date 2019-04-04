package com.ccsip.coap.master.metadata;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class CoapMetadataApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoapMetadataApplication.class, args);
	}

	public static ConfigurableApplicationContext getSpringBeanContext() {
		return SpringApplication.run(CoapMetadataApplication.class, new String[] {});
	}
}
