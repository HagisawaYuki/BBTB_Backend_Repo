package com.example.demo.response;

import lombok.Data;

@Data
public class EditCreditCardPaymentDataResponse {

	private int year;
	private int month;
	private int day;
	private int amount;
	private int creditCardID;
}
