package com.exercice.threadsDemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@SpringBootTest
class ThreadsDemoApplicationTests {

	@Test
	void contextLoads() {
		assertNotNull(ThreadsDemoApplication.class);
	}

}
