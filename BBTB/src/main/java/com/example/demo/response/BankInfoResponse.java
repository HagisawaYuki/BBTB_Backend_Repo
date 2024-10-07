package com.example.demo.response;

import java.util.List;

import com.example.demo.json.CreditCardJson;
import com.example.demo.json.SalaryJson;

import lombok.Data;

@Data
public class BankInfoResponse {

	private int id;
	
	private String name;
	
	private String bank_name;
	
	private int Balance;
	
	private List<CreditCardJson> credit_cards;
	
	private List<SalaryJson> salaries;
}
