package com.example.demo.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Table(name = "bank_info")
@Entity
@Data
@AllArgsConstructor
public class BankInfo {
	
	//ID
	@Id
	private int id;

	//銀行名（オリジナル）
	private String name;
	
	//銀行名（e.g. 三井住友銀行、ゆうちょ銀行）
	private String bank_name;
	
	//口座残金
	private int balance;
	
	//ユーザID
	private String userID;
	
	public BankInfo() {
		
	}
}
