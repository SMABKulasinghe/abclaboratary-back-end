package com.abclaboratary.abclaboratary.service;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;

public interface ReportService {

	JSONObject createReports(JSONObject createReports);

	JSONObject getAllReports(HttpServletRequest request);

	JSONObject getParametersForId(HttpServletRequest request, long id);

	JSONObject approveOrRejectReport(HttpServletRequest request, long id, long reId);

	JSONObject getDoctorAndReportList(HttpServletRequest request);

	JSONObject submitReportForAppoinmentData(JSONObject reportForAppoinmentData);

}
