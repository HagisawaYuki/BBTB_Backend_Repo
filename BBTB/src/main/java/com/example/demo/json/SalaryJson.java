package com.example.demo.json;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SalaryJson {

	
	private int id;
	
	private String name;
	
	private int year;
	
	private int month;
	
	private int day;
	
	private int amount;
	
	public SalaryJson() {}
}
