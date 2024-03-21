package com.abclaboratary.abclaboratary.controller;


import com.abclaboratary.abclaboratary.common.Common;
import com.abclaboratary.abclaboratary.service.AppoinmentService;
import com.abclaboratary.abclaboratary.service.AuthService;
import com.abclaboratary.abclaboratary.service.ReportService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/appoinment")
public class AppoinmentController {
	
	@Autowired
	ReportService reportService;
	
	@Autowired
	AppoinmentService appoinmentService;
	
	
	@PostMapping(value = "/book-appoinment")
	public ResponseEntity<JSONObject> bookAppoinment(@RequestBody JSONObject appoinementDetails) {
		JSONObject data = null;
		try {
			data = appoinmentService.bookAppoinment(appoinementDetails);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<JSONObject>(data, HttpStatus.OK);
	}
	
	@GetMapping("/get-appoinment-list")
	public ResponseEntity<JSONObject> getAppoinmentList(HttpServletRequest request){
		try {

			JSONObject data=appoinmentService.getAppoinmentList(request);
			return new ResponseEntity<JSONObject>(data, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@GetMapping("/get-appoinment-form/{id}")
	public ResponseEntity<JSONObject> getAppoinmentForm(HttpServletRequest request,@PathVariable Long id){
		try {

			JSONObject data=appoinmentService.getAppoinmentForm(request,id);
			return new ResponseEntity<JSONObject>(data, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@GetMapping("/get-appoinment-details-for-patient/{userid}")
	public ResponseEntity<JSONObject> getAppoinemtDetailsForPatient(HttpServletRequest request,@PathVariable Long userid){
		try {

			JSONObject data=appoinmentService.getAppoinemtDetailsForPatient(request, userid);
			return new ResponseEntity<JSONObject>(data, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@GetMapping("/get-report-submitted-details/{id}")
	public ResponseEntity<JSONObject> getReportSubmittedDetails(HttpServletRequest request,@PathVariable long id){
		try {

			JSONObject data=appoinmentService.getReportSubmittedDetails(request,id);
			return new ResponseEntity<JSONObject>(data, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@GetMapping("/email-test")
	public ResponseEntity<JSONObject> emailtest(){
		try {

			Properties properties = System.getProperties();
			boolean debug = false;
		
			String email ="abclaboratory.lab@gmail.com";
			String Password ="ogal ljpi eywu evru";
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
			
			
			
			Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(email, Password);
				}
			});

			Message message = new MimeMessage(session);

			message.addFrom(new InternetAddress[] { new InternetAddress("abclaboratory.lab@gmail.com") });
			
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("smab.kulasinghe@gmail.com"));

			
			message.setSubject("wORKING");
			message.setContent("wORKING", "text/html");
			
			Transport.send(message);

			System.out.println("Done");

		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return null;
			
	}
	

}
