package com.abclaboratary.abclaboratary.controller;


import com.abclaboratary.abclaboratary.service.AppoinmentService;
import com.abclaboratary.abclaboratary.service.AuthService;
import com.abclaboratary.abclaboratary.service.ReportService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

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
	

}
