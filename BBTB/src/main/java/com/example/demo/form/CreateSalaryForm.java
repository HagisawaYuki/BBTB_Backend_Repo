package com.example.demo.form;

import lombok.Data;

@Data
public class CreateSalaryForm {

	private int bankID;
	private String name;
    private int year;
    private int month;
    private int day;
    private int amount;
}
