package com.abclaboratary.abclaboratary.service;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

public interface AppoinmentService {

	JSONObject bookAppoinment(JSONObject appoinementDetails);

	JSONObject getAppoinmentList(HttpServletRequest request);

//	JSONObject getAppoinmentForm(HttpServletRequest request, long id);

	JSONObject getAppoinmentForm(HttpServletRequest request, Long appoinmentId);

	JSONObject getAppoinemtDetailsForPatient(HttpServletRequest request, Long userid);

	JSONObject getReportSubmittedDetails(HttpServletRequest request, long id);

	
	
}
