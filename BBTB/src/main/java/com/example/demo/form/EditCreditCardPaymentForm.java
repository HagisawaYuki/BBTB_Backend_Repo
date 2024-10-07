package com.example.demo.form;

import lombok.Data;

@Data
public class EditCreditCardPaymentForm {

	private int id;
	private int year;
	private int month;
	private int day;
	private int amount;
	private int creditCardID;
}
