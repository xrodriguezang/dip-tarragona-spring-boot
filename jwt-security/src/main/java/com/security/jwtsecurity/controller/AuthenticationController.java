package com.security.jwtsecurity.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.jwtsecurity.model.AuthenticationRequest;
import com.security.jwtsecurity.model.AuthenticationResponse;
import com.security.jwtsecurity.model.RegisterRequest;
import com.security.jwtsecurity.service.AuthenticationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {
	
	private final AuthenticationService authenticationService;
	
	@PostMapping("/register")
	public AuthenticationResponse register(@RequestBody RegisterRequest request) {
		return authenticationService.register(request);
	}
	
	@PostMapping("/authenticate")
	public AuthenticationResponse authenticate(@RequestBody AuthenticationRequest request) {
		return authenticationService.authenticate(request);
	}
	
}
