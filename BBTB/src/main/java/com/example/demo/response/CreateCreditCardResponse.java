package com.example.demo.response;

import lombok.Data;

@Data
public class CreateCreditCardResponse {

	private Boolean isRegister;
	private String errorMessage;
}
