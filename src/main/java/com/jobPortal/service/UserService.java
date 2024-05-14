package com.jobPortal.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jobPortal.dao.UserRepo;
import com.jobPortal.model.User;

@Service
public class UserService {
	
	@Autowired
	UserRepo repo;
	
	@Autowired
	AuthenticationManager authManager;
	
	@Autowired
	JwtService jwtService;
	
	BCryptPasswordEncoder encode = new BCryptPasswordEncoder(12);

	public User register(User user) {
		// TODO Auto-generated method stub
		user.setPassword(encode.encode(user.getPassword()));
		return repo.save(user);
	}

	public String login(User user) {
		// TODO Auto-generated method stub
		Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.getJwt(user.getUsername());
		}
		return "failed";
		
	}

}
