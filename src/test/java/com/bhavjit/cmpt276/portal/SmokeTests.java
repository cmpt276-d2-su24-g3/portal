package com.bhavjit.cmpt276.portal;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.bhavjit.cmpt276.portal.controllers.UserController;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class SmokeTests {

	@LocalServerPort
	private int port;

	@Autowired
	private UserController controller;

	@Test
	void contextLoads() throws Exception {
		assertNotNull(controller);
	}

}
