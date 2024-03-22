package com.abclaboratary.abclaboratary.serviceimpl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.abclaboratary.abclaboratary.common.JwtUtils;
import com.abclaboratary.abclaboratary.common.PasswordUtils;
import com.abclaboratary.abclaboratary.entity.Patient;
import com.abclaboratary.abclaboratary.entity.User;
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
		// TODO Auto-generated method stub
		return null;
	}

}
