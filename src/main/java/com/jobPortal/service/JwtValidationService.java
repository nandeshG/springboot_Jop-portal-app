package com.jobPortal.service;

import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtValidationService extends OncePerRequestFilter {

	@Autowired
	JwtService jwt;
	
	@Autowired
	ApplicationContext context;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String authHeader = request.getHeader("Authorization");
		String username=null;
		String token = null;
		if(authHeader !=null && authHeader.startsWith("Bearer ")) {
			token=authHeader.substring(7);
			username = jwt.extractUserName(token);
		}
		
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
			 UserDetails userDetais=context.getBean(MyService.class).loadUserByUsername(username);
			 if(jwt.ValidateToken(token,userDetais)) {
				 UsernamePasswordAuthenticationToken authToken = 
						 new UsernamePasswordAuthenticationToken(username, null,userDetais.getAuthorities());
				 authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				 SecurityContextHolder.getContext().setAuthentication(authToken);
				 
			 }
		}
		filterChain.doFilter(request, response);
		
	}

}
