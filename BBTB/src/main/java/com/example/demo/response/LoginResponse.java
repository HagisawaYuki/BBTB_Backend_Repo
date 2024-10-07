package com.example.demo.response;

import lombok.Data;

@Data
public class LoginResponse {
	private Boolean isLogin;
	private String errorMessage;
}
