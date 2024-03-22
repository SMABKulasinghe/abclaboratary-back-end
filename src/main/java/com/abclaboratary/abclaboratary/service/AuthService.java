package com.abclaboratary.abclaboratary.service;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

public interface AuthService {

	JSONObject registerPatient(JSONObject registerPatient);

	JSONObject login(JSONObject login);

	JSONObject registerUser(JSONObject registerUser);

}
