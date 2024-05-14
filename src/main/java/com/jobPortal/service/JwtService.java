package com.jobPortal.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	String secreteKey;
	
	public JwtService() throws NoSuchAlgorithmException {
		secreteKey = genrateScreteKey();
	}


	private String genrateScreteKey() throws NoSuchAlgorithmException {
		KeyGenerator keyGen=KeyGenerator.getInstance("hmacSHA256");
		SecretKey secret= keyGen.generateKey();
		return Base64.getEncoder().encodeToString(secret.getEncoded());
	}
	
	public String getJwt(String username) {
		// TODO Auto-generated method stub
		
		Map<String, Object> claims = new HashMap<>();
			
		
		return Jwts.builder()
		.setClaims(claims)
		.setSubject(username)
		.setIssuedAt(new Date(System.currentTimeMillis()))
		.setExpiration(new Date(System.currentTimeMillis()+ 1000*60*30))
		.signWith(getKey(),SignatureAlgorithm.HS256).compact();
		
		
	}
	
	public Key getKey(){
		byte[] bytes = Decoders.BASE64.decode(secreteKey);
		return Keys.hmacShaKeyFor(bytes);
	}


	 public String extractUserName(String token) {
	        // extract the username from jwt token
	        return extractClaim(token, Claims::getSubject);
	    }

	    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
	        final Claims claims = extractAllClaims(token);
	        return claimResolver.apply(claims);
	    }

	    private Claims extractAllClaims(String token) {
	        return Jwts.parserBuilder()
	                .setSigningKey(getKey())
	                .build().parseClaimsJws(token).getBody();
	    }

	    public boolean ValidateToken(String token, UserDetails userDetails) {
	        final String userName = extractUserName(token);
	        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	    }

	    private boolean isTokenExpired(String token) {
	        return extractExpiration(token).before(new Date());
	    }

	    private Date extractExpiration(String token) {
	        return extractClaim(token, Claims::getExpiration);
	    }
	

}
