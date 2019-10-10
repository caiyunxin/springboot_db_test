package com.example.demo.controller;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.net.URL;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

/**
* @author：Administrator
* @createDate:2019-09-26 39:11
* @description:HelloControllerIT
*/
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HelloControllerIT {
	@LocalServerPort
	private int port;
	
	private URL base;
	
	@Autowired
	private TestRestTemplate template;
	
	@Before
	public void setUp() throws Exception{
		this.base = new URL("http://localhost:" + port + "/hello/");
		System.out.println("base = " + base.toString());
	}
	
	@Test
	public void hetHello() throws Exception{
		ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
		assertThat(response.getBody() , equalTo("Greetings from Spring Boot"));
	}
}
