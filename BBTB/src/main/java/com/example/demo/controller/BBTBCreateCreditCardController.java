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
import com.example.demo.form.CreateCreditCardForm;
import com.example.demo.response.CreateCreditCardResponse;
import com.example.demo.service.CreditCardInfoService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@RequiredArgsConstructor
public class BBTBCreateCreditCardController {

	private final CreditCardInfoService creditCardService;
	private final MessageSource messageSource;
	
	public CreateCreditCardForm createCreditCardFormData = new CreateCreditCardForm();
	
	@GetMapping("/bbtb/create/credit_card")
	@ResponseBody
	public CreateCreditCardResponse create_credit_card() {
		var creditCardInfoOpt = creditCardService.resistCreditCardInfo(createCreditCardFormData);
		if(creditCardInfoOpt.isEmpty()) {
			CreateCreditCardResponse responce = new CreateCreditCardResponse();
			responce.setIsRegister(false);
			responce.setErrorMessage(AppUtil.getMessage(messageSource, MessageConst.BBTB_CREATE_CREDITCARD_WRONG_NAME_INPUT));
			return responce;
		}else {
			CreateCreditCardResponse responce = new CreateCreditCardResponse();
			responce.setIsRegister(true);
			responce.setErrorMessage("");
			return responce;
		}
	}
	
	//ポストでフォーム情報を取得
	@PostMapping("/bbtb/create/credit_card")
	public void setCreateCreditCardFormData(@RequestBody CreateCreditCardForm form) {
		this.createCreditCardFormData.setName(form.getName());
		this.createCreditCardFormData.setBankID(form.getBankID());
	}
}
