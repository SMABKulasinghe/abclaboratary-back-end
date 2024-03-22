package com.abclaboratary.abclaboratary.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.print.Doc;
import javax.servlet.http.HttpServletRequest;

import com.abclaboratary.abclaboratary.common.Common;
import com.abclaboratary.abclaboratary.common.JwtUtils;
import com.abclaboratary.abclaboratary.common.PasswordUtils;
import com.abclaboratary.abclaboratary.entity.Doctor;
import com.abclaboratary.abclaboratary.entity.LabTechnician;
import com.abclaboratary.abclaboratary.entity.Patient;
import com.abclaboratary.abclaboratary.entity.User;
import com.abclaboratary.abclaboratary.repo.DoctorRepo;
import com.abclaboratary.abclaboratary.repo.LabTechnicianRepo;
import com.abclaboratary.abclaboratary.repo.PatientRepo;
import com.abclaboratary.abclaboratary.repo.UserRepo;
import com.abclaboratary.abclaboratary.service.AuthService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Service;

@Service
@EnableJpaRepositories("com.abclaboratary.abclaboratary.repo")
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	PatientRepo patientRepo;
	
	@Autowired
	Common common;
	
	@Autowired
	DoctorRepo doctorRepo;
	
	@Autowired
	LabTechnicianRepo labTechnicianRepo;

	@Override
	public JSONObject registerPatient(JSONObject registerPatient) {
		JSONObject data = new JSONObject();
		String status = null;
		String statusCode = null;
		try {
			System.out.println("inside");
			
			String name = registerPatient.get("name").toString();
			String phone = registerPatient.get("phone").toString();
			String email = registerPatient.get("email").toString();
			String dob = registerPatient.get("dob").toString();
			String gender = registerPatient.get("gender").toString();
			String password = registerPatient.get("password").toString();
			String rePassword = registerPatient.get("repassword").toString();
			String userRole = registerPatient.get("userRole").toString();
			String regex = "\\+94[1-9]\\d{8}";
			//Correct phone no - +94711664123
			
			Pattern pattern = Pattern.compile(regex);		
			Matcher matcher = pattern.matcher(phone);
			
			if(!userRepo.existsByUserEmail(email)) {
				if(matcher.matches()) {
					if(password.equals(rePassword)) {
						
						PasswordUtils passwordUtils = new PasswordUtils();
			            String encodedPassword = passwordUtils.encodePassword(password);
			            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			            Date dobDate = dateFormat.parse(dob);
						
						User user = new User();
						user.setUserName(name != null ? name : "");
						user.setUserEmail(email != null ? email : "");
						user.setUserPassword(encodedPassword != null ? encodedPassword : "");
						user.setUserPhone(phone);
						user.setStatus(Long.valueOf(2));
						user.setUserRole(Long.valueOf(userRole));
						user = userRepo.save(user);
						
						Patient patient = new Patient();
						patient.setPatientDob(dobDate);
						patient.setPatientEmail(email);
						patient.setPatientName(name);
						patient.setPatientPhone(phone);
						patient.setPatientGender(gender);
						patient.setStatus(Long.valueOf(2));
						patient.setUserId(user.getUserId());
						patientRepo.save(patient);
						
						status = "Success";
						statusCode = "00";
					}else {
						status = "Password not match";
						statusCode = "04";
					}
				}else {
					status = "Phone number is wrong. Please make as : +94711231230";
					statusCode = "06";
				}
				
			}else {
				status = "Email alread exist";
				statusCode = "05";
			}

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
	public JSONObject login(JSONObject login) {
		JSONObject data = new JSONObject();
		try {
			
			String email = login.get("email").toString();
			String password = login.get("password").toString();
			
            Optional<User> optUser = userRepo.findByUserEmail(email);
            if (!optUser.isPresent()) {
            	data.put("status", "04");
        		data.put("statusCode", "Invalid Email");
            } else {
                
                User user = optUser.get();
                String encodedPassword = user.getUserPassword();
                PasswordUtils passwordUtils = new PasswordUtils();
                Boolean flag = passwordUtils.isPasswordValid(password, encodedPassword);
                if (flag) {
                    JwtUtils jwtUtils = new JwtUtils();
                    String token = jwtUtils.generateToken(user.getUserEmail());
                    
                    JSONObject data2 = new JSONObject();
//                    data.put("userId", user.getUserId());
//                    data2.put("userEmail", user.getUserEmail());

                    data.put("userrole", user.getUserRole());
                    data.put("userdata", user.getUserId());
                    data.put("token", token);
                    data.put("status", "00");
            		data.put("statusCode", "LogedIn");
            		return data;
                } else {
                    data.put("status", "02");
            		data.put("statusCode", "Invalid Password");
                }
            }

        } catch (Exception e) {
        	e.printStackTrace();
        	data.put("status", "03");
    		data.put("statusCode", "Error");
        }
		
		return data;
	}

	@Override
	public JSONObject registerUser(JSONObject registerUser) {
		JSONObject data = new JSONObject();
		String status = null;
		String statusCode = null;
		try {
			System.out.println("inside");
			
			String name = registerUser.get("name").toString();
			String email = registerUser.get("email").toString();
			String dob = registerUser.get("dob").toString();
			String nic = registerUser.get("nic").toString();
			String telephone = registerUser.get("telephone").toString();
			String gender = registerUser.get("gender").toString();
			String registrationno = registerUser.get("registrationno").toString();
			String position = registerUser.get("position").toString();
			
			String regex = "\\+94[1-9]\\d{8}";
			//Correct phone no - +94711664123
			
			Pattern pattern = Pattern.compile(regex);		
			Matcher matcher = pattern.matcher(telephone);
			
			if(!userRepo.existsByUserEmail(email)) {
				if(matcher.matches()) {
					
					String randompassword = common.generateRandomPassword();
						
					
					
						PasswordUtils passwordUtils = new PasswordUtils();
			            String encodedPassword = passwordUtils.encodePassword(randompassword);
			            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			            Date dobDate = dateFormat.parse(dob);
			            
			            Doctor doc = new Doctor();
			            LabTechnician lt = new LabTechnician();
			            
			            Long postition1 = null;
			            if(position.equals("Doctor")) {
			            	postition1 = 2L;
			            	
			            	String spelist = "Speaclist";
			            	
			            	User user = new User();
							user.setUserName(name != null ? name : "");
							user.setUserEmail(email != null ? email : "");
							user.setUserPassword(encodedPassword != null ? encodedPassword : "");
							user.setUserPhone(telephone);
							user.setStatus(Long.valueOf(2));
							user.setUserRole(Long.valueOf(postition1));
							user = userRepo.save(user);
							
							doc.setDoctorDob(dobDate);
							doc.setDoctorEmail(email);
							doc.setDoctorName(name);
							doc.setDoctorPhone(telephone);
							doc.setDoctorRegistrationNo(registrationno);
							doc.setDoctorSpecialization(spelist);
							doc.setGender(gender);
							doc.setStatus(11L);
							doc.setUserId(user.getUserId());
							doc = doctorRepo.save(doc);
			            	
			            }else if(position.equals("Technician")) {
			            	postition1 = 4L;
			            	
			            	User user = new User();
							user.setUserName(name != null ? name : "");
							user.setUserEmail(email != null ? email : "");
							user.setUserPassword(encodedPassword != null ? encodedPassword : "");
							user.setUserPhone(telephone);
							user.setStatus(Long.valueOf(2));
							user.setUserRole(Long.valueOf(postition1));
							user = userRepo.save(user);
			            	
			            	lt.setGender(gender);
			            	lt.setLabTechnicianDob(dobDate);
			            	lt.setLabTechnicianEmail(email);
			            	lt.setLabTechnicianName(name);
			            	lt.setLabTechnicianPhone(telephone);
			            	lt.setStatus(18L);
			            	lt.setUserId(user.getUserId());
			            	lt.setLabTechnicianRegisterNo(registrationno);
			            	labTechnicianRepo.save(lt);
			            }
						
						
						String emailabove = common.getEmailabove();
						String emailmiddle = "<div style=\"line-height: 140%; text-align: left; word-wrap: break-word;\">"
								+ "<p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 18px; line-height: 25.2px; color: #666666;\">Hello,</span></p>"
								+ "<p style=\"font-size: 14px; line-height: 140%;\"> </p>"
								+ "<p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 18px; line-height: 25.2px; color: #666666;\">Dear "+name+",</span></p>"
								+ "<p style=\"font-size: 14px; line-height: 140%;\"> </p>"
								+ "<p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 18px; line-height: 25.2px; color: #666666;\">Please login to ABC Laboratory system using this email ("+email+") and this password: "+randompassword+".</span></p>"
								+ "<p style=\"font-size: 14px; line-height: 140%;\"> </p>"
								+ "<p style=\"font-size: 14px; line-height: 140%;\"><span style=\"font-size: 18px; line-height: 25.2px; color: #666666;\">Thank you</span></p>"
								
								+ "</div>";
						String emailbelow = common.getEmailabelow();
						
						String completeEmail = emailabove+emailmiddle+emailbelow;
						
						common.sendEMail(email, "ABC Laboratory", completeEmail);
						
						status = "Success";
						statusCode = "00";
					
				}else {
					status = "Phone number is wrong. Please make as : +94711231230";
					statusCode = "06";
				}
				
			}else {
				status = "Email alread exist";
				statusCode = "05";
			}

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
