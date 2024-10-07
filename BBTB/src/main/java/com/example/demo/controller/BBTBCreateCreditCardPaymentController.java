package com.example.demo.controller;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.MessageConst;
import com.example.demo.form.CreateCreditCardPaymentForm;
import com.example.demo.response.CreateCreditCardPaymentResponse;
import com.example.demo.service.CreditCardPaymentService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@RequiredArgsConstructor
public class BBTBCreateCreditCardPaymentController {
	
	private final CreditCardPaymentService creditCardPaymentService;
	private final MessageSource messageSource;
	
	public CreateCreditCardPaymentForm createCreditCardPaymentFormData = new CreateCreditCardPaymentForm();

	@GetMapping("/bbtb/create/credit_card_payment")
	@ResponseBody
	public CreateCreditCardPaymentResponse create_credit_card_payment() {
		var creditCardPaymentOpt = creditCardPaymentService.resistCreditCardPayment(createCreditCardPaymentFormData);
		if(creditCardPaymentOpt.isEmpty()) {
			CreateCreditCardPaymentResponse responce = new CreateCreditCardPaymentResponse();
			responce.setIsRegister(false);
			responce.setErrorMessage(AppUtil.getMessage(messageSource, MessageConst.BBTB_CREATE_CREDITCARDPAYMENT_WRONG_NAME_INPUT));
			return responce;
		}else {
			CreateCreditCardPaymentResponse responce = new CreateCreditCardPaymentResponse();
			responce.setIsRegister(true);
			responce.setErrorMessage("");
			return responce;
		}
	}
	
	//ポストでフォーム情報を取得
	@PostMapping("/bbtb/create/credit_card_payment")
	public void setCreateCreditCardPaymentFormData(@RequestBody CreateCreditCardPaymentForm form) {
		this.createCreditCardPaymentFormData.setYear(form.getYear());
		this.createCreditCardPaymentFormData.setMonth(form.getMonth());
		this.createCreditCardPaymentFormData.setDay(form.getDay());
		this.createCreditCardPaymentFormData.setCreditCardID(form.getCreditCardID());
		this.createCreditCardPaymentFormData.setAmount(form.getAmount());
	}
}
