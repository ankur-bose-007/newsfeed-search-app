package com.mocktest.core.security;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
@Component
public class JwtProvider {
	@Value("${secretCode}")
	private String secretCode;
	
	@Value("${expirationTime}")
	private int expirationTime;
	
	private static final Logger logger=LoggerFactory.getLogger(JwtProvider.class);
	
	public String generateJwtToken(Authentication authentication){
		return Jwts.builder().setSubject(((org.springframework.security.core.userdetails.User)authentication.getPrincipal()).getUsername())
							.setIssuedAt(new Date())
							.setExpiration(new Date(new Date().getTime()+expirationTime))
							.signWith(SignatureAlgorithm.HS512, secretCode)
							.compact();
	}
	
	public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
			                .setSigningKey(secretCode)
			                .parseClaimsJws(token)
			                .getBody().getSubject();
    }
 
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(secretCode).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Invalid JWT signature -> Message: {} ", e);
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token -> Message: {}", e);
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token -> Message: {}", e);
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token -> Message: {}", e);
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty -> Message: {}", e);
        }
        return false;
    }
}
