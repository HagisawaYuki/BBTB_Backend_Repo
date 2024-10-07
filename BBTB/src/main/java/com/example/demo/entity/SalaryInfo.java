package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Table(name = "salary_info")
@Entity
@Data
@AllArgsConstructor
public class SalaryInfo {

	//給料ID
	@Id
	private int id;
	
	//給料名
	private String name;
		
	//給料年
	private int year;
		
	//給料月
	private int month;
		
	//給料日
	private int day;
		
	//給料料金
	private int amount;
		
	//銀行ID
	private int bankID;
		
	public SalaryInfo() {
		
	}
}
