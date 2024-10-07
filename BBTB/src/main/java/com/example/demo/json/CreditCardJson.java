package com.example.demo.json;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCardJson {

//	@SerializedName("id")
//    @Expose
//    public int id;
//	
//    @SerializedName("name")
//    @Expose
//    public String name;
//    
//    @SerializedName("credit_card_payments")
//    @Expose
//    public List<CreditCardPaymentJson> credit_card_payments = null;
	
	private int id;
	
	private String name;
	
	private List<CreditCardPaymentJson> credit_card_payments;
	
	public CreditCardJson() {}
}
