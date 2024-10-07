package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Table(name = "user_info")
@Entity
@Data
@AllArgsConstructor
public class UserInfo {
	//ユーザ名
	@Id
	@Column(name = "id")
	private String userID;
	
	//パスワード
	private String password;
	
	public UserInfo() {
    }
}

