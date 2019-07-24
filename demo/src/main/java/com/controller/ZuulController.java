package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ZuulController {

	@RequestMapping("/available")
	public String available() {
		return "Spring in Action";
	}

	@RequestMapping("/checked-out")
	public String checkedOut() {
		return "Spring Boot in Action";
	}
}
