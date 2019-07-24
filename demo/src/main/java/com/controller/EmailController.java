package com.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.EmailService;

@RestController
public class EmailController {

	@Autowired
	EmailService emailService;

	@RequestMapping("/sendEmail")
	public String sendEmail() throws AddressException, MessagingException, IOException {
		emailService.sendEmail();
		return "Email sent successfully";
	}
}
