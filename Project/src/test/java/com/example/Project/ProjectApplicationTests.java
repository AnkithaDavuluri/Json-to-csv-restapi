package com.example.Project;

import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProjectApplicationTests {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	void shouldReturnCreated() {
		ResponseEntity<String> response = restTemplate
				.withBasicAuth("Anki", "test@123")
				.getForEntity("/api/convert/input", String.class);
			assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
	}
	@Test
	void shouldReturnFilenotfound() {
		ResponseEntity<String> response = restTemplate
				.withBasicAuth("Anki", "test@123")
				.getForEntity("/api/convert/file", String.class);
			assertThat(response.getBody()).isEqualTo("File Not Found");
	}

}
