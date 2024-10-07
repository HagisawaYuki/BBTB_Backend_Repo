package com.example.demo.json;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreditCardPaymentJson {
//
//	@SerializedName("id")
//    @Expose
//    public int id;
//	
//    @SerializedName("year")
//    @Expose
//    public int year;
//    
//    @SerializedName("month")
//    @Expose
//    public int month;
//    
//    @SerializedName("day")
//    @Expose
//    public int day;
//    
//    @SerializedName("amount")
//    @Expose
//    public int amount;
	
	private int id;
	
	private int year;
	
	private int month;
	
	private int day;
	
	private int amount;
	
	public CreditCardPaymentJson() {}
	
}
