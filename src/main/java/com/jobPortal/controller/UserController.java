package com.jobPortal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobPortal.model.User;
import com.jobPortal.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserService service;
	
	@PostMapping("register")
	public User register(@RequestBody User user) {
		return service.register(user);
	}

	@PostMapping("login")
	public String login(@RequestBody User user) {
		
		return service.login(user);
	}
}
