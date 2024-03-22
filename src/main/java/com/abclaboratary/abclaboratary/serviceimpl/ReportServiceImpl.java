package com.abclaboratary.abclaboratary.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.abclaboratary.abclaboratary.common.Common;
import com.abclaboratary.abclaboratary.common.JwtUtils;
import com.abclaboratary.abclaboratary.common.PasswordUtils;
import com.abclaboratary.abclaboratary.entity.Appoinment;
import com.abclaboratary.abclaboratary.entity.Doctor;
import com.abclaboratary.abclaboratary.entity.Patient;
import com.abclaboratary.abclaboratary.entity.Report;
import com.abclaboratary.abclaboratary.entity.ReportDetails;
import com.abclaboratary.abclaboratary.entity.ReportSubmittedDetails;
import com.abclaboratary.abclaboratary.entity.User;
import com.abclaboratary.abclaboratary.repo.AppoinmentRepo;
import com.abclaboratary.abclaboratary.repo.DoctorRepo;
import com.abclaboratary.abclaboratary.repo.PatientRepo;
import com.abclaboratary.abclaboratary.repo.ReportDetailsRepo;
import com.abclaboratary.abclaboratary.repo.ReportRepo;
import com.abclaboratary.abclaboratary.repo.ReportSubmittedDetailsRepo;
import com.abclaboratary.abclaboratary.repo.UserRepo;
import com.abclaboratary.abclaboratary.service.AuthService;
import com.abclaboratary.abclaboratary.service.ReportService;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Service
@EnableJpaRepositories("com.abclaboratary.abclaboratary.repo")
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	ReportRepo reportRepo;
	
	@Autowired
	ReportDetailsRepo reportDetailsRepo;
	
	@Autowired
	DoctorRepo doctorRepo;
	
	@Autowired
	AppoinmentRepo appoinmentRepo;
	
	@Autowired
	ReportSubmittedDetailsRepo reportSubmittedDetailsRepo;
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PatientRepo patientRepo;
	
	@Autowired
	Common common;

	@Override
	public JSONObject createReports(JSONObject createReports) {
		JSONObject data = new JSONObject();
		String status = null;
		String statusCode = null;
		
		
		try {
			
			String logedin = createReports.get("logedin").toString();
			
			if(logedin.equals("logedin")) {
				
				String reportName = createReports.get("reportName").toString();
				String reportDescription = createReports.get("reportDescription").toString();
				String reportPreparation = createReports.get("reportPreparation").toString();
				
				Report report = new Report();
				report.setReportName(reportName);
				report.setReportDescription(reportDescription);
				report.setReportPreperation(reportPreparation);
				report.setStatus(9L);
				report = reportRepo.save(report);
				
				ArrayList parameterDataArray = (ArrayList) createReports.get("parameterData");

				for (Object parameterName : parameterDataArray) {

					String parmeternme = (String) (((HashMap) parameterName).get("parameterName"));
					String parameterScales = (String) (((HashMap) parameterName).get("parameterScales"));
					String femaleRange = (String) (((HashMap) parameterName).get("femaleRange"));
					String maleRange = (String) (((HashMap) parameterName).get("maleRange"));
					
					ReportDetails reportDetails = new ReportDetails();
					reportDetails.setParameterName(parmeternme);
					reportDetails.setParameterScale(parameterScales);
					reportDetails.setMaleRange(maleRange);
					reportDetails.setFemaleRange(femaleRange);
					reportDetails.setReportId(report.getReportId());
					reportDetails.setStatus(9L);
					reportDetailsRepo.save(reportDetails);
					
					
				}
				
				
				status = "Success";
				statusCode = "00";
				
			}else {
				
				status = "Please login";
				statusCode = "04";
				
			}
			
			data.put("status", status);
			data.put("statusCode", statusCode);

        } catch (Exception e) {
        	e.printStackTrace();
        	data.put("status", "03");
    		data.put("statusCode", "Error");
        }
		return data;
	}

	@Override
	public JSONObject getAllReports(HttpServletRequest request) {
		JSONObject data = new JSONObject();
		try {
			
			JSONArray ar = new JSONArray();
			List<Report> reports = reportRepo.findAll();
			for(Report report : reports) {
				JSONObject ob = new JSONObject();
				ob.put("reportName", report.getReportName());
				ob.put("reportDescription", report.getReportDescription());
				ob.put("preperation", report.getReportPreperation());
				ob.put("id", report.getReportId());
				ob.put("status", report.getStatus());
				
				ar.add(ob);
			}
			
			data.put("table", ar);
			data.put("status", "00");
			data.put("statusCode", "Success");
			return data;

		} catch (Exception e) {
			e.printStackTrace();
			
			data.put("status", "03");
			data.put("statusCode", "Error");
			return data;
			
		}
	}

	@Override
	public JSONObject getParametersForId(HttpServletRequest request, long id) {
		JSONObject data = new JSONObject();
		try {
			
			JSONArray ar = new JSONArray();
			List<ReportDetails> reports = reportDetailsRepo.findByReportId(id);
			for(ReportDetails report : reports) {
				JSONObject ob = new JSONObject();
				ob.put("parameterName", report.getParameterName());
				ob.put("parameterDescription", report.getParameterScale());
				ob.put("maleRange", report.getMaleRange());
				ob.put("femaleRange", report.getFemaleRange());
				ob.put("id", report.getReportDetailsId());
				ob.put("status", report.getStatus());
				
				ar.add(ob);
			}
			
			Optional<Report> re = reportRepo.findById(id);
			
			data.put("reName", re.get().getReportName());
			data.put("reDes", re.get().getReportDescription());
			data.put("reId", re.get().getReportId());
			data.put("reStatus", re.get().getStatus());
			data.put("table", ar);
			data.put("status", "00");
			data.put("statusCode", "Success");
			return data;

		} catch (Exception e) {
			e.printStackTrace();
			
			data.put("status", "03");
			data.put("statusCode", "Error");
			return data;
			
		}
	}

	@Override
	public JSONObject approveOrRejectReport(HttpServletRequest request, long id, long reId) {
		JSONObject data = new JSONObject();
		try {
			
			Optional<Report> re = reportRepo.findById(reId);
			if(re.isPresent()) {
				re.get().setStatus(id);
				reportRepo.save(re.get());
			}
			
			
			data.put("status", "Success");
			data.put("statusCode", "00");
			return data;

		} catch (Exception e) {
			e.printStackTrace();
			
			data.put("status", "Error");
			data.put("statusCode", "03");
			return data;
			
		}
	}

	@Override
	public JSONObject getDoctorAndReportList(HttpServletRequest request) {
		JSONObject data = new JSONObject();
		try {
			
			JSONArray ar1 = new JSONArray();
			List<Report> reports = reportRepo.findAllByStatus(7L);
			for(Report report : reports) {
				JSONObject ob = new JSONObject();
				ob.put("reportName", report.getReportName());
				ob.put("reportDescription", report.getReportDescription());
				ob.put("id", report.getReportId());
				ob.put("status", report.getStatus());
				
				ar1.add(ob);
			}
			
			JSONArray ar2 = new JSONArray();
			List<Doctor> doctors = doctorRepo.findAllByStatus(11L);
			for(Doctor doctor : doctors) {
				JSONObject ob = new JSONObject();
				ob.put("doctorName", doctor.getDoctorName());
				ob.put("doctorSpecilization", doctor.getDoctorSpecialization());
				ob.put("id", doctor.getDoctorId());
				ob.put("status", doctor.getStatus());
				
				ar2.add(ob);
			}
			
			
			data.put("reportList", ar1);
			data.put("doctorList", ar2);
			data.put("status", "00");
			data.put("statusCode", "Success");
			return data;

		} catch (Exception e) {
			e.printStackTrace();
			
			data.put("status", "03");
			data.put("statusCode", "Error");
			return data;
			
		}
	}

	@Override
	public JSONObject submitReportForAppoinmentData(JSONObject reportForAppoinmentData) {
		JSONObject data = new JSONObject();
		String status = null;
		String statusCode = null;
		
		
		try {
			
				
				String appoinmentid = reportForAppoinmentData.get("appoinmentid").toString();
				System.out.println("appoinmentid-"+appoinmentid);
				
				if(!reportSubmittedDetailsRepo.existsByAppoinmentId(Long.valueOf(appoinmentid))) {
					Optional<Appoinment> appoinment = appoinmentRepo.findById(Long.valueOf(appoinmentid));
					appoinment.get().setStatus(15L);
					appoinmentRepo.save(appoinment.get());
					
					Optional<Report> report = reportRepo.findById(appoinment.get().getReportId());
					
					ArrayList parameterDataArray = (ArrayList) reportForAppoinmentData.get("formDataArray");

					for (Object parameterName : parameterDataArray) {

						String id = (String) (((HashMap) parameterName).get("id"));
						String value = (String) (((HashMap) parameterName).get("value"));
						
						Optional<ReportDetails> rd = reportDetailsRepo.findById(Long.valueOf(id));
						
						ReportSubmittedDetails rds = new ReportSubmittedDetails();
						rds.setFemaleRange(rd.get().getFemaleRange());
						rds.setMaleRange(rd.get().getMaleRange());
						rds.setParameterName(rd.get().getParameterName());
						rds.setParameterScale(rd.get().getParameterScale());
						rds.setReportDetailsId(rd.get().getReportDetailsId());
						rds.setReportId(report.get().getReportId());
						rds.setAppoinmentId(appoinment.get().getAppoinmentId());
						rds.setResults(value);
						rds.setStatus(15L);
						reportSubmittedDetailsRepo.save(rds);
					}
					
					Optional<Patient> p = patientRepo.findByUserId(appoinment.get().getUserId());
					Optional<User> u = userRepo.findById(appoinment.get().getUserId());
					
					String emailabove = common.getEmailabove();
					String emailmiddle = "<div style=\"line-height: 140%; text-align: left; word-wrap: break-word;\">"
							+ "<p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 18px; line-height: 25.2px; color: #666666;\">Hello,</span></p>"
							+ "<p style=\"font-size: 14px; line-height: 140%;\"> </p>"
							+ "<p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 18px; line-height: 25.2px; color: #666666;\">Dear "+p.get().getPatientName()+",</span></p>"
							+ "<p style=\"font-size: 14px; line-height: 140%;\"> </p>"
							+ "<p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 18px; line-height: 25.2px; color: #666666;\">Your Report is Ready. Please login to ABC Laboratory system and go to the Report tab and check your report.</span></p>"
							+ "<p style=\"font-size: 14px; line-height: 140%;\"> </p>"
							+ "<p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 18px; line-height: 25.2px; color: #666666;\">Thank you</span></p>"
							
							+ "</div>";
					String emailbelow = common.getEmailabelow();
					
					String completeEmail = emailabove+emailmiddle+emailbelow;
					
					String getToEmail = u.get().getUserEmail();
					System.out.println("getToEmail=============="+getToEmail);
					
					common.sendEMail(getToEmail, "Report Details", completeEmail);
					
					status = "Success";
					statusCode = "00";
				}else {
					status = "Duplicate";
					statusCode = "04";
				}
				
				
				
				
				
			
			data.put("status", status);
			data.put("statusCode", statusCode);

        } catch (Exception e) {
        	e.printStackTrace();
        	data.put("status", "03");
    		data.put("statusCode", "Error");
        }
		return data;
	}

	

}
