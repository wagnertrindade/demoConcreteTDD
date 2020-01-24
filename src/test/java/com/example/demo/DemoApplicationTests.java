package com.example.demo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DemoApplicationTests {

	@Value("${local.server.port}")
	Integer port = 0;

	protected String url(String path) {
		return "http://localhost:" + port + path;
	}
}
