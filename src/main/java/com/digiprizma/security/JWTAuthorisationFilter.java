package com.digiprizma.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * 
 * @author SKAN
 *
 */
public class JWTAuthorisationFilter extends OncePerRequestFilter {

	@Autowired
	AuthenticationManager authenticationManager;
	/*
	 * 
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		System.out.println("**********************");
		/*
		 * Faire les tests necessaires
		 */
		String jwt = request.getHeader("Authorization");
		if(jwt == null) throw new RuntimeException("Not Autorised");
		filterChain.doFilter(request, response);
	}

}
