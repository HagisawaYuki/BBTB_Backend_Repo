package com.example.demo.form;

import lombok.Data;

@Data
public class CreateCreditCardPaymentForm {

    private int creditCardID;
    private int year;
    private int month;
    private int day;
    private int amount;
}
