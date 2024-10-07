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
import com.example.demo.form.CreateBankForm;
import com.example.demo.response.CreateBankResponse;
import com.example.demo.service.BankInfoService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@RequiredArgsConstructor
public class BBTBCreateBankController {
	
	private final BankInfoService bankService;
	private final MessageSource messageSource;
	
	public CreateBankForm createBankFormData = new CreateBankForm();

	@GetMapping("/bbtb/create/bank")
	@ResponseBody
	public CreateBankResponse create_bank() {
		var bankInfoOpt = bankService.resistBankInfo(createBankFormData);
		if(bankInfoOpt.isEmpty()) {
			CreateBankResponse responce = new CreateBankResponse();
			responce.setIsRegister(false);
			responce.setErrorMessage(AppUtil.getMessage(messageSource, MessageConst.BBTB_CREATE_BANK_WRONG_NAME_INPUT));
			return responce;
		}else {
			CreateBankResponse responce = new CreateBankResponse();
			responce.setIsRegister(true);
			responce.setErrorMessage("");
			return responce;
		}
	}
	
	//ポストでフォーム情報を取得
	@PostMapping("/bbtb/create/bank")
	public void setCreateBankFormData(@RequestBody CreateBankForm bank) {
		this.createBankFormData.setName(bank.getName());
		this.createBankFormData.setBank_name(bank.getBank_name());
		this.createBankFormData.setBalance(bank.getBalance());
	}
}
