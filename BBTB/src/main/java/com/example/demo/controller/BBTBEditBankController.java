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
import com.example.demo.form.EditBankForm;
import com.example.demo.request.EditBankDataRequest;
import com.example.demo.response.EditBankDataResponse;
import com.example.demo.response.EditBankResponse;
import com.example.demo.service.BankInfoService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@RequiredArgsConstructor
public class BBTBEditBankController {
	
	private final BankInfoService bankService;
	private final MessageSource messageSource;

	public EditBankForm editBankFormData = new EditBankForm();
	public EditBankDataRequest editBankDataRequest = new EditBankDataRequest();
	
	@GetMapping("/bbtb/edit/bank")
	@ResponseBody
	public EditBankResponse edit_bank() {
		var bankInfoOpt = bankService.editBankInfo(editBankFormData);
		if(bankInfoOpt.isEmpty()) {
			EditBankResponse responce = new EditBankResponse();
			responce.setIsEdit(false);
			responce.setErrorMessage(AppUtil.getMessage(messageSource, MessageConst.BBTB_EDIT_BANK_WRONG_NAME_INPUT));
			return responce;
		}else {
			EditBankResponse responce = new EditBankResponse();
			responce.setIsEdit(true);
			responce.setErrorMessage("");
			return responce;
		}
	}
	
	//ポストでフォーム情報を取得
	@PostMapping("/bbtb/edit/bank")
	public void setEditBankFormData(@RequestBody EditBankForm bank) {
		this.editBankFormData.setId(bank.getId());
		this.editBankFormData.setName(bank.getName());
		this.editBankFormData.setBank_name(bank.getBank_name());
		this.editBankFormData.setBalance(bank.getBalance());
	}
	
	@GetMapping("/bbtb/edit/bankdata")
	@ResponseBody
	public EditBankDataResponse edit_bank_data() {
		var bankInfoOpt = bankService.searchBankByID(editBankDataRequest.getId());
		//System.out.println(bankInfoOpt.get());
		if(bankInfoOpt.isEmpty()) {
			EditBankDataResponse responce = new EditBankDataResponse();
			return responce;
		}else {
			EditBankDataResponse responce = new EditBankDataResponse();
			responce.setName(bankInfoOpt.get().getName());
			responce.setBank_name(bankInfoOpt.get().getBank_name());
			responce.setBalance(bankInfoOpt.get().getBalance());
			return responce;
		}
	}
	
	//ポストでフォーム情報を取得
	@PostMapping("/bbtb/edit/bankdata")
	public void setEditBankData(@RequestBody EditBankDataRequest bank) {
		this.editBankDataRequest.setId(bank.getId());
	}
}
