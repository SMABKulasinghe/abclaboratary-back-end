package com.abclaboratary.abclaboratary.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.abclaboratary.abclaboratary.common.JwtUtils;
import com.abclaboratary.abclaboratary.common.PasswordUtils;
import com.abclaboratary.abclaboratary.entity.Appoinment;
import com.abclaboratary.abclaboratary.entity.Doctor;
import com.abclaboratary.abclaboratary.entity.Patient;
import com.abclaboratary.abclaboratary.entity.Report;
import com.abclaboratary.abclaboratary.entity.ReportDetails;
import com.abclaboratary.abclaboratary.entity.User;
import com.abclaboratary.abclaboratary.repo.AppoinmentRepo;
import com.abclaboratary.abclaboratary.repo.DoctorRepo;
import com.abclaboratary.abclaboratary.repo.PatientRepo;
import com.abclaboratary.abclaboratary.repo.ReportDetailsRepo;
import com.abclaboratary.abclaboratary.repo.ReportRepo;
import com.abclaboratary.abclaboratary.repo.UserRepo;
import com.abclaboratary.abclaboratary.service.AppoinmentService;
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
public class AppoinementServiceImpl implements AppoinmentService {
	
	@Autowired
	ReportRepo reportRepo;
	
	@Autowired
	ReportDetailsRepo reportDetailsRepo;
	
	@Autowired
	DoctorRepo doctorRepo;
	
	@Autowired
	AppoinmentRepo appoinmentRepo;

	@Override
	public JSONObject bookAppoinment(JSONObject appoinementDetails) {
		JSONObject data = new JSONObject();
		String status = null;
		String statusCode = null;
		try {
			System.out.println("inside");
			
			String doctorid = appoinementDetails.get("doctorid").toString();
			String reportid = appoinementDetails.get("reportid").toString();
			String appoinmentDate = appoinementDetails.get("appoinmentDate").toString();
			String userid = appoinementDetails.get("userid").toString();
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date aDate = dateFormat.parse(appoinmentDate);
            
            Appoinment app = new Appoinment();
            app.setAppoinmentDate(aDate);
            app.setDoctorId(Long.valueOf(doctorid));
            app.setReportId(Long.valueOf(reportid));
            app.setUserId(Long.valueOf(userid));
            app.setStatus(16L);
            app = appoinmentRepo.save(app);
            
            app.setAppoinmentNo("APINMNT"+app.getAppoinmentId());
            appoinmentRepo.save(app);
						
			status = "Success";
			statusCode = "00";

		}catch(Exception e) {
			e.printStackTrace();
			status = "Error";
			statusCode = "03";
		} 
		
		data.put("status", status);
		data.put("statusCode", statusCode);
		data.put("data", "");
		return data;
	}

	

	

}
