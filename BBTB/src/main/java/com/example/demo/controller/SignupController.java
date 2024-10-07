package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.MessageConst;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.response.SignupResponse;
import com.example.demo.service.UserInfoService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@RequiredArgsConstructor
public class SignupController {
	public SignupForm formData = new SignupForm();
	
	private final UserInfoService service;
	private final MessageSource messageSource;

	@GetMapping("/signup")
	@ResponseBody
	public SignupResponse signup() {
		var userInfoOpt = service.resistUserInfo(formData);
		if(userInfoOpt.isEmpty()) {
			SignupResponse responce = new SignupResponse();
			responce.setIsSignup(false);
			responce.setErrorMessage(AppUtil.getMessage(messageSource, MessageConst.SIGNUP_WRONG_USERID_INPUT));
			return responce;
		}else {
			SignupResponse responce = new SignupResponse();
			responce.setIsSignup(true);
			responce.setErrorMessage("");
			return responce;
		}
	}
	
	//ポストでフォーム情報を取得
	@PostMapping("/signup")
	public void setFormData(@RequestBody UserInfo user) {
		this.formData.setUserID(user.getUserID());
		this.formData.setPassword(user.getPassword());
	}
	
}
