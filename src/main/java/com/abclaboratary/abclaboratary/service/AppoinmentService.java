package com.abclaboratary.abclaboratary.service;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

public interface AppoinmentService {

	JSONObject bookAppoinment(JSONObject appoinementDetails);

	
	
}
