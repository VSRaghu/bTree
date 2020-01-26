package com.fractal.btree;

import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import com.fractal.btree.BtreeApplication;

@EnableAutoConfiguration
@Configuration
@SpringBootTest(classes = BtreeApplication.class)
class BtreeApplicationTests {

	@Test
	void contextLoads() {
	}

}
