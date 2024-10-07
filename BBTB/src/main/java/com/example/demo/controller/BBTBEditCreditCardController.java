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
import com.example.demo.form.EditCreditCardForm;
import com.example.demo.request.EditCreditCardDataRequest;
import com.example.demo.response.EditCreditCardDataResponse;
import com.example.demo.response.EditCreditCardResponse;
import com.example.demo.service.CreditCardInfoService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;



@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@RequiredArgsConstructor
public class BBTBEditCreditCardController {
	
	private final CreditCardInfoService creditCardService;
	private final MessageSource messageSource;

	public EditCreditCardForm editCreditCardFormData = new EditCreditCardForm();
	public EditCreditCardDataRequest editCreditCardDataRequest = new EditCreditCardDataRequest();
	
	@GetMapping("/bbtb/edit/credit_card")
	@ResponseBody
	public EditCreditCardResponse edit_credit_card() {
		var creditCardInfoOpt = creditCardService.editCreditCardInfo(editCreditCardFormData);
		if(creditCardInfoOpt.isEmpty()) {
			EditCreditCardResponse responce = new EditCreditCardResponse();
			responce.setIsEdit(false);
			//responce.setErrorMessage("名前が被ってしまっています。");
			responce.setErrorMessage(AppUtil.getMessage(messageSource, MessageConst.BBTB_EDIT_CREDITCARD_WRONG_NAME_INPUT));
			return responce;
		}else {
			EditCreditCardResponse responce = new EditCreditCardResponse();
			responce.setIsEdit(true);
			responce.setErrorMessage("");
			return responce;
		}
	}
	
	//ポストでフォーム情報を取得
	@PostMapping("/bbtb/edit/credit_card")
	public void setEditCreditCardFormData(@RequestBody EditCreditCardForm creditCard) {
		this.editCreditCardFormData.setId(creditCard.getId());
		this.editCreditCardFormData.setName(creditCard.getName());
		this.editCreditCardFormData.setBankID(creditCard.getBankID());
	}
	
	@GetMapping("/bbtb/edit/creditcarddata")
	@ResponseBody
	public EditCreditCardDataResponse edit_credit_card_data() {
		var CreditCardInfoOpt = creditCardService.searchCreditCardByID(editCreditCardDataRequest.getId());
		//System.out.println(bankInfoOpt.get());
		if(CreditCardInfoOpt.isEmpty()) {
			EditCreditCardDataResponse responce = new EditCreditCardDataResponse();
			
			return responce;
		}else {
			EditCreditCardDataResponse responce = new EditCreditCardDataResponse();
			responce.setName(CreditCardInfoOpt.get().getCredit_card_name());
			responce.setBankID(CreditCardInfoOpt.get().getBankID());
			return responce;
		}
	}
	
	//ポストでフォーム情報を取得
	@PostMapping("/bbtb/edit/creditcarddata")
	public void setEditCreditCardData(@RequestBody EditCreditCardDataRequest creditCard) {
		this.editCreditCardDataRequest.setId(creditCard.getId());
	}
}
