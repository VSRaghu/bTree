package com.fractal.btree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@EnableAutoConfiguration
@Configuration
@ComponentScan(basePackages = {"com.fractal.btree.*"})
@SpringBootApplication
public class BtreeApplication {

	public static void main(String[] args) {
		SpringApplication.run(BtreeApplication.class, args);
	}

}
