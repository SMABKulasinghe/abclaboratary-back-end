package com.abclaboratary.abclaboratary.controller;


import com.abclaboratary.abclaboratary.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@CrossOrigin
@RestController
@RequestMapping(value = "/auth")
public class AuthController {
	
	@Autowired
    AuthService authService;
	
	@PostMapping(value = "/login")
	public ResponseEntity<JSONObject> login(@RequestBody JSONObject login) {
		JSONObject data = null;
		try {
			data = authService.login(login);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<JSONObject>(data, HttpStatus.OK);
	}
	  
	@PostMapping(value = "/register-patient")
	public ResponseEntity<JSONObject> registerPatient(@RequestBody JSONObject registerPatient) {
		JSONObject data = null;
		try {
			data = authService.registerPatient(registerPatient);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<JSONObject>(data, HttpStatus.OK);
	}
	
	@PostMapping(value = "/register-user")
	public ResponseEntity<JSONObject> registerUser(@RequestBody JSONObject registerUser) {
		JSONObject data = null;
		try {
			data = authService.registerUser(registerUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<JSONObject>(data, HttpStatus.OK);
	}
	
	

}
