package com.example.demo.response;

import java.util.List;

import com.example.demo.json.CreditCardPaymentJson;

import lombok.Data;

@Data
public class CreditCardInfoResponse {

	private int id;
	
	private String name;
	
	private List<CreditCardPaymentJson> credit_card_payments;
}
