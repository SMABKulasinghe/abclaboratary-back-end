package com.abclaboratary.abclaboratary.serviceimpl;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
import com.abclaboratary.abclaboratary.entity.ReportSubmittedDetails;
import com.abclaboratary.abclaboratary.entity.User;
import com.abclaboratary.abclaboratary.repo.AppoinmentRepo;
import com.abclaboratary.abclaboratary.repo.DoctorRepo;
import com.abclaboratary.abclaboratary.repo.PatientRepo;
import com.abclaboratary.abclaboratary.repo.ReportDetailsRepo;
import com.abclaboratary.abclaboratary.repo.ReportRepo;
import com.abclaboratary.abclaboratary.repo.ReportSubmittedDetailsRepo;
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
	
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PatientRepo patientRepo;
	
	@Autowired
	ReportSubmittedDetailsRepo reportSubmittedDetailsRepo;

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

	@Override
	public JSONObject getAppoinmentList(HttpServletRequest request) {
		JSONObject data = new JSONObject();
		try {
			
			JSONArray ar1 = new JSONArray();
			List<Appoinment> appoinments = appoinmentRepo.findAllByStatus(16L);
			for(Appoinment appoinment : appoinments) {
				JSONObject ob = new JSONObject();
				
				Optional<Report> report = reportRepo.findById(appoinment.getReportId());
				Optional<User> user = userRepo.findById(appoinment.getUserId());
				
				ob.put("appoinmentno", appoinment.getAppoinmentNo());
				ob.put("username", user.get().getUserName());
				ob.put("reportname", report.get().getReportName());
				ob.put("id", appoinment.getAppoinmentId());
//				ob.put("status", report.getStatus());
				
				ar1.add(ob);
			}
			
			data.put("appoinmentlist", ar1);
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
	public JSONObject getAppoinmentForm(HttpServletRequest request,Long appoinmentId) {
		JSONObject data = new JSONObject();
		try {
			
			JSONArray ar1 = new JSONArray();
			
			Optional<Appoinment> appoinment = appoinmentRepo.findById(appoinmentId);
			
			if(appoinment.isPresent()) {
				Appoinment appnmnt = appoinment.get();
				
				Optional<Report> rep = reportRepo.findById(appnmnt.getReportId());
				List<ReportDetails> repDetails = reportDetailsRepo.findByReportId(rep.get().getReportId());
				for(ReportDetails repDetail : repDetails) {
					JSONObject ob = new JSONObject();
					
					ob.put("reportdetailid", repDetail.getReportDetailsId());
					ob.put("reportdetailname", repDetail.getParameterName());
					ob.put("reportdetailscale", repDetail.getParameterScale());
					ob.put("reportdetailmale", repDetail.getMaleRange());
					ob.put("reportdetailfemale", repDetail.getFemaleRange());
	
					
					ar1.add(ob);
				}
				
				Optional<User> user = userRepo.findById(appnmnt.getUserId());
				Optional<Patient> pa = patientRepo.findByUserId(user.get().getUserId());
				
				data.put("patientname", pa.get().getPatientName());
				Date dob = pa.get().getPatientDob();
				data.put("patientgender", pa.get().getPatientGender());
				data.put("patientdob", pa.get().getPatientDob().getYear()+"/"+pa.get().getPatientDob().getMonth()+"/"+pa.get().getPatientDob().getDate());
				data.put("appoinmentid", appnmnt.getAppoinmentId());
				data.put("appoinmentno", appnmnt.getAppoinmentNo());
				data.put("reportid", appnmnt.getReportId());
				data.put("userid", appnmnt.getUserId());
				data.put("doctorid", appnmnt.getDoctorId());
			}
			
			
			
					
			data.put("reportdetails", ar1);
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
	public JSONObject getAppoinemtDetailsForPatient(HttpServletRequest request, Long userid) {
		JSONObject data = new JSONObject();
		try {
			
			JSONArray ar = new JSONArray();
			List<Appoinment> apinmts = appoinmentRepo.findByUserId(userid);
			for(Appoinment apinmt : apinmts) {
				JSONObject ob = new JSONObject();
				
				Optional<Report> r = reportRepo.findById(apinmt.getReportId());
				
				ob.put("appoinmentno", apinmt.getAppoinmentNo());
				ob.put("appoinmentdate", apinmt.getAppoinmentDate());
				ob.put("appoinmentid", apinmt.getAppoinmentId());
				ob.put("reportname", r.get().getReportDescription());
				ob.put("reportpre", r.get().getReportPreperation());
				ob.put("status", apinmt.getStatus());
				
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
	public JSONObject getReportSubmittedDetails(HttpServletRequest request, long id) {
		JSONObject data = new JSONObject();
		try {
			
			JSONArray ar = new JSONArray();
			Optional<Appoinment> apinmt = appoinmentRepo.findById(id);
			Optional<Patient> p = patientRepo.findByUserId(apinmt.get().getUserId());
			
			List<ReportSubmittedDetails> rptSubDetails = reportSubmittedDetailsRepo.findByAppoinmentId(id);
			for(ReportSubmittedDetails rptSubDetail : rptSubDetails) {
				JSONObject ob = new JSONObject();
				ob.put("parametername", rptSubDetail.getParameterName());
				ob.put("parameterscale", rptSubDetail.getParameterScale());
				if(p.get().getPatientGender().equals("Male")) {
					ob.put("gender", rptSubDetail.getMaleRange());
				}else if(p.get().getPatientGender().equals("Female")) {
					ob.put("gender", rptSubDetail.getFemaleRange());
				}
				ob.put("result", rptSubDetail.getResults());
				
				ar.add(ob);
			}
			
			Optional<Report> r = reportRepo.findById(apinmt.get().getReportId());
			
			data.put("table", ar);
			data.put("reportname", r.get().getReportDescription());
			data.put("patientname", p.get().getPatientName());
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

	

	

}
