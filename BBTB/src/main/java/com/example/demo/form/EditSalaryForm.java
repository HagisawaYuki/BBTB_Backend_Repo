package com.example.demo.form;

import lombok.Data;

@Data
public class EditSalaryForm {

	private int id;
	private String name;
	private int year;
	private int month;
	private int day;
	private int amount;
	private int bankID;
}
