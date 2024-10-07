package com.example.demo.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.authentication.LoginUserDetails;
import com.example.demo.response.LogoutResponse;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@RequiredArgsConstructor
public class LogoutController {

	@GetMapping("/logout")
	@ResponseBody
	public LogoutResponse logout() {
		LoginUserDetails.setIsLogin(false);
		LoginUserDetails.setLoginUser(null);
		LogoutResponse response = new LogoutResponse();
		response.setIsLogout(true);
		return response;
	}
}
