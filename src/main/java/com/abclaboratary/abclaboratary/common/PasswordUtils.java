package com.abclaboratary.abclaboratary.common;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtils {
	
	public static String encodePassword(String password){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        return encodedPassword;
    }

    public static Boolean isPasswordValid(String password, String encodedPassword){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Boolean flag = passwordEncoder.matches(password,encodedPassword);
        return flag;
    }

}
