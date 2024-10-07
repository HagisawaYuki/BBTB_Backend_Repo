package com.example.demo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.UserInfo;
import com.example.demo.form.SignupForm;
import com.example.demo.repository.UserInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserInfoService {

	/**ユーザ情報テーブルDAO*/
	private final UserInfoRepository repository;
	
	public Optional<UserInfo> searchUsers(String id){
		return repository.findById(id);
	}
	
	public Optional<UserInfo> resistUserInfo(SignupForm form){
		
		var userInfoExistedOpt = repository.findById(form.getUserID());
		if(userInfoExistedOpt.isPresent()) {
			return Optional.empty();
		}
		var userInfo = new UserInfo();
		userInfo.setUserID(form.getUserID());
		
		userInfo.setPassword(form.getPassword());
		
		return Optional.of(repository.save(userInfo));
	}
}
