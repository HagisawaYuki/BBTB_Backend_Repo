package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Table(name = "credit_card_payment")
@Entity
@Data
@AllArgsConstructor
public class CreditCardPayment {

	//クレカ支払いID
	@Id
	private int id;
	
	//クレカ支払い年
	private int year;
	
	//クレカ支払い月
	private int month;
	
	//クレカ支払い日
	private int day;
	
	//クレカ支払い料金
	private int amount;
	
	//クレカID
	private int credit_cardID;
	
	public CreditCardPayment() {
		
	}
	
}
