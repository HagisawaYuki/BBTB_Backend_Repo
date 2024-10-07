package com.example.demo.json;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankJson {

//	@SerializedName("id")
//    @Expose
//    public int id;
//    @SerializedName("name")
//    @Expose
//    public String name;
//    @SerializedName("bank_name")
//    @Expose
//    public String bank_name;
//    @SerializedName("balance")
//    @Expose
//    public int balance;
//    @SerializedName("credit_cards")
//    @Expose
//    public List<CreditCardJson> credit_cards = null;
	private int id;
	
	private String name;
	
	private String bank_name;
	
	private int Balance;
	
	private List<CreditCardJson> credit_cards;
	
	private List<SalaryJson> salaries;
	
	public BankJson(){}
}
