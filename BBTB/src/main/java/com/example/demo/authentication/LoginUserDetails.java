package com.example.demo.authentication;

import com.example.demo.entity.UserInfo;

import lombok.Data;

@Data
public class LoginUserDetails {
	public static Boolean getIsLogin() {
		return isLogin;
	}

	public static void setIsLogin(Boolean isLogin) {
		LoginUserDetails.isLogin = isLogin;
	}

	public static UserInfo getLoginUser() {
		return loginUser;
	}

	public static void setLoginUser(UserInfo loginUser) {
		LoginUserDetails.loginUser = loginUser;
	}

	private static Boolean isLogin;
	
	private static UserInfo loginUser;
}
