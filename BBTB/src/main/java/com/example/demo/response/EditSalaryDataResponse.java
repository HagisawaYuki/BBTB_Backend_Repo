package com.example.demo.response;

import lombok.Data;

@Data
public class EditSalaryDataResponse {

	private String name;
	private int year;
	private int month;
	private int day;
	private int amount;
	private int bankID;
}
