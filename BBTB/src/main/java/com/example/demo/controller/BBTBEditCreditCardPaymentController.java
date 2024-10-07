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
import com.example.demo.form.EditCreditCardPaymentForm;
import com.example.demo.request.EditCreditCardPaymentDataRequest;
import com.example.demo.response.EditCreditCardPaymentDataResponse;
import com.example.demo.response.EditCreditCardPaymentResponse;
import com.example.demo.service.CreditCardPaymentService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@RequiredArgsConstructor
public class BBTBEditCreditCardPaymentController {
	
	private final CreditCardPaymentService creditCardPaymentService;
	private final MessageSource messageSource;
	
	public EditCreditCardPaymentForm editCreditCardPaymentFormData = new EditCreditCardPaymentForm();
	public EditCreditCardPaymentDataRequest editCreditCardPaymentDataRequest = new EditCreditCardPaymentDataRequest();
	
	@GetMapping("/bbtb/edit/credit_card_payment")
	@ResponseBody
	public EditCreditCardPaymentResponse edit_credit_card_payment() {
		var creditCardPaymentInfoOpt = creditCardPaymentService.editCreditCardPayment(editCreditCardPaymentFormData);
		if(creditCardPaymentInfoOpt.isEmpty()) {
			EditCreditCardPaymentResponse responce = new EditCreditCardPaymentResponse();
			responce.setIsEdit(false);
			//responce.setErrorMessage("名前が被ってしまっています。");
			responce.setErrorMessage(AppUtil.getMessage(messageSource, MessageConst.BBTB_EDIT_CREDITCARDPAYMENT_WRONG_NAME_INPUT));
			return responce;
		}else {
			EditCreditCardPaymentResponse responce = new EditCreditCardPaymentResponse();
			responce.setIsEdit(true);
			responce.setErrorMessage("");
			return responce;
		}
	}
	
	//ポストでフォーム情報を取得
	@PostMapping("/bbtb/edit/credit_card_payment")
	public void setEditCreditCardPaymentFormData(@RequestBody EditCreditCardPaymentForm creditCardPayment) {
		this.editCreditCardPaymentFormData.setId(creditCardPayment.getId());
		this.editCreditCardPaymentFormData.setYear(creditCardPayment.getYear());
		this.editCreditCardPaymentFormData.setMonth(creditCardPayment.getMonth());
		this.editCreditCardPaymentFormData.setDay(creditCardPayment.getDay());
		this.editCreditCardPaymentFormData.setAmount(creditCardPayment.getAmount());
		this.editCreditCardPaymentFormData.setCreditCardID(creditCardPayment.getCreditCardID());
	}
	
	@GetMapping("/bbtb/edit/creditcardpaymentdata")
	@ResponseBody
	public EditCreditCardPaymentDataResponse edit_credit_card_payment_data() {
		var CreditCardPaymentInfoOpt = creditCardPaymentService.searchCreditCardPaymentByID(editCreditCardPaymentDataRequest.getId());
		if(CreditCardPaymentInfoOpt.isEmpty()) {
			EditCreditCardPaymentDataResponse responce = new EditCreditCardPaymentDataResponse();
			return responce;
		}else {
			EditCreditCardPaymentDataResponse responce = new EditCreditCardPaymentDataResponse();
			responce.setYear(CreditCardPaymentInfoOpt.get().getYear());
			responce.setMonth(CreditCardPaymentInfoOpt.get().getMonth());
			responce.setDay(CreditCardPaymentInfoOpt.get().getDay());
			responce.setAmount(CreditCardPaymentInfoOpt.get().getAmount());
			responce.setCreditCardID(CreditCardPaymentInfoOpt.get().getCredit_cardID());
			return responce;
		}
	}
	
	//ポストでフォーム情報を取得
	@PostMapping("/bbtb/edit/creditcardpaymentdata")
	public void setEditCreditCardPaymentData(@RequestBody EditCreditCardPaymentDataRequest creditCardPayment) {
		this.editCreditCardPaymentDataRequest.setId(creditCardPayment.getId());
	}
}
