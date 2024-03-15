package com.abclaboratary.abclaboratary.common;

import java.util.Date;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JwtUtils {
	
	private static final String SECRET = "hashi@98";
    private static final long EXPIRATION_TIME = 86400000;
    private static final String ISSUER = "Token Provider";

    public static String generateToken(String username) {
        Date expirationDate = new Date(System.currentTimeMillis() + EXPIRATION_TIME);
        Algorithm algorithm = Algorithm.HMAC256(SECRET);

        String token = JWT.create()
                .withIssuer(ISSUER)
                .withExpiresAt(expirationDate)
                .withSubject(username)
                .sign(algorithm);

        return token;
    }
    
    public static boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build()
                    .verify(token);
            return true;
        } catch (JWTVerificationException exception) {
            // Invalid signature/claims
            return false;
        }
    }
    
    public static void TokenVerification(String auth) {
        try {
            String[] parts = auth.split(" ");
            String token;
            if (parts.length < 2) {
                token = parts[0];
            } else {
                token = parts[1];
            }
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm).withIssuer(ISSUER).build();
            verifier.verify(token);
        } catch (Exception e) {
        	e.printStackTrace();
//            if (e.getMessage() == null) {
//                throw new BadRequestAlertException("Unauthorized", "JWTUtils", "generateJWTToken");
//            } else {
//                throw new BadRequestAlertException(e.getMessage(), "JWTUtils", "generateJWTToken");
//            }
        }
    }

}
