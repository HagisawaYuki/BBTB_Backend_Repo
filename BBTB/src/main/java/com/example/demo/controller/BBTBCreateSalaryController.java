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
import com.example.demo.form.CreateSalaryForm;
import com.example.demo.response.CreateSalaryResponse;
import com.example.demo.service.SalaryInfoService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@RequiredArgsConstructor
public class BBTBCreateSalaryController {

	private final SalaryInfoService salaryInfoService;
	private final MessageSource messageSource;
	
	public CreateSalaryForm createSalaryFormData = new CreateSalaryForm();

	@GetMapping("/bbtb/create/salary")
	@ResponseBody
	public CreateSalaryResponse create_salary() {
		var salaryOpt = salaryInfoService.resistSalary(createSalaryFormData);
		if(salaryOpt.isEmpty()) {
			CreateSalaryResponse responce = new CreateSalaryResponse();
			responce.setIsRegister(false);
			responce.setErrorMessage(AppUtil.getMessage(messageSource, MessageConst.BBTB_CREATE_SALARY_WRONG_NAME_INPUT));
			return responce;
		}else {
			CreateSalaryResponse responce = new CreateSalaryResponse();
			responce.setIsRegister(true);
			responce.setErrorMessage("");
			return responce;
		}
	}
	
	//ポストでフォーム情報を取得
	@PostMapping("/bbtb/create/salary")
	public void setCreateSalaryFormData(@RequestBody CreateSalaryForm form) {
		this.createSalaryFormData.setName(form.getName());
		this.createSalaryFormData.setYear(form.getYear());
		this.createSalaryFormData.setMonth(form.getMonth());
		this.createSalaryFormData.setDay(form.getDay());
		this.createSalaryFormData.setBankID(form.getBankID());
		this.createSalaryFormData.setAmount(form.getAmount());
	}
	
}
