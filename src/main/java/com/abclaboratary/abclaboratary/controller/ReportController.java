package com.abclaboratary.abclaboratary.controller;


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
@RequestMapping(value = "/report")
public class ReportController {
	
	@Autowired
	ReportService reportService;
	
	  
	@PostMapping(value = "/create-reports")
	public ResponseEntity<JSONObject> createReports(@RequestBody JSONObject createReports) {
		JSONObject data = null;
		try {
			data = reportService.createReports(createReports);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<JSONObject>(data, HttpStatus.OK);
	}
	
	@GetMapping("/get-all-reports")
	public ResponseEntity<JSONObject> getAllReports(HttpServletRequest request){
		try {

			JSONObject data=reportService.getAllReports(request);
			return new ResponseEntity<JSONObject>(data, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@GetMapping("/get-parameter-for/{id}")
	public ResponseEntity<JSONObject> getParametersForId(HttpServletRequest request,@PathVariable long id){
		try {

			JSONObject data=reportService.getParametersForId(request,id);
			return new ResponseEntity<JSONObject>(data, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@GetMapping("/approve-or-reject-report/{id}/{reId}")
	public ResponseEntity<JSONObject> approveOrRejectReport(HttpServletRequest request,@PathVariable long id,
			@PathVariable long reId){
		try {

			JSONObject data=reportService.approveOrRejectReport(request,id,reId);
			return new ResponseEntity<JSONObject>(data, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	@GetMapping("/get-doctor-and-report-list")
	public ResponseEntity<JSONObject> getDoctorAndReportList(HttpServletRequest request){
		try {

			JSONObject data=reportService.getDoctorAndReportList(request);
			return new ResponseEntity<JSONObject>(data, HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
			
	}
	
	

}
