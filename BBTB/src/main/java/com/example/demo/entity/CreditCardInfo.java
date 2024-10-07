package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Table(name = "credit_card_info")
@Entity
@Data
@AllArgsConstructor
public class CreditCardInfo {

	//クレカ名（オリジナル）
	@Id
	private int id;
	
	@Column(name="name")
	private String credit_card_name;
	
	//銀行ID
	private int bankID;
	
	public CreditCardInfo() {
		
	}
}
