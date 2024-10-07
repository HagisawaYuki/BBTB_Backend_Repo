package com.example.demo.controller;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.authentication.LoginUserDetails;
import com.example.demo.constant.MessageConst;
import com.example.demo.entity.UserInfo;
import com.example.demo.form.LoginForm;
import com.example.demo.response.LoginResponse;
import com.example.demo.service.UserInfoService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@RequiredArgsConstructor
public class LoginController {
	public LoginForm formData = new LoginForm();
	private final UserInfoService service;
	private final MessageSource messageSource;
	@GetMapping("/login")
	@ResponseBody
	public LoginResponse getUserInfo() {
		//データベースからユーザ情報取得
		Optional<UserInfo> user = service.searchUsers(formData.getUserID());
		//ログイン成功か検証
		if(!user.isEmpty()) {
			//パスワードが正しいか
			if(user.get().getPassword().equals(formData.getPassword())) {
				LoginResponse responce = new LoginResponse();
				responce.setIsLogin(true);
				responce.setErrorMessage("");
				
				LoginUserDetails.setIsLogin(true);
				LoginUserDetails.setLoginUser(user.get());
				return responce;
			}else {
				LoginResponse responce = new LoginResponse();
				responce.setIsLogin(false);
				responce.setErrorMessage(AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_PASSWORD_INPUT));
				
				LoginUserDetails.setIsLogin(false);
				LoginUserDetails.setLoginUser(null);
				return responce;
			}
		}else {
			LoginResponse responce = new LoginResponse();
			responce.setIsLogin(false);
			responce.setErrorMessage(AppUtil.getMessage(messageSource, MessageConst.LOGIN_WRONG_USERID_INPUT));
			LoginUserDetails.setIsLogin(false);
			LoginUserDetails.setLoginUser(null);
			return responce;
		}
	}

	@PostMapping("/login")
	public void login(@RequestBody UserInfo user) {
		this.formData.setUserID(user.getUserID());
		this.formData.setPassword(user.getPassword());
	}
}
