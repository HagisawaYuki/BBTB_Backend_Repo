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
import com.example.demo.form.EditSalaryForm;
import com.example.demo.request.EditSalaryDataRequest;
import com.example.demo.response.EditSalaryDataResponse;
import com.example.demo.response.EditSalaryResponse;
import com.example.demo.service.SalaryInfoService;
import com.example.demo.util.AppUtil;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@RequiredArgsConstructor
public class BBTBEditSalaryController {

	private final SalaryInfoService salaryInfoService;
	private final MessageSource messageSource;
	
	public EditSalaryForm editSalaryFormData = new EditSalaryForm();
	public EditSalaryDataRequest editSalaryDataRequest = new EditSalaryDataRequest();
	
	@GetMapping("/bbtb/edit/salary")
	@ResponseBody
	public EditSalaryResponse edit_salary() {
		var salaryInfoOpt = salaryInfoService.editSalary(editSalaryFormData);
		if(salaryInfoOpt.isEmpty()) {
			EditSalaryResponse responce = new EditSalaryResponse();
			responce.setIsEdit(false);
			responce.setErrorMessage(AppUtil.getMessage(messageSource, MessageConst.BBTB_EDIT_SALARY_WRONG_NAME_INPUT));
			return responce;
		}else {
			EditSalaryResponse responce = new EditSalaryResponse();
			responce.setIsEdit(true);
			responce.setErrorMessage("");
			return responce;
		}
	}
	
	//ポストでフォーム情報を取得
	@PostMapping("/bbtb/edit/salary")
	public void setEditSalaryFormData(@RequestBody EditSalaryForm salary) {
		this.editSalaryFormData.setId(salary.getId());
		this.editSalaryFormData.setName(salary.getName());
		this.editSalaryFormData.setYear(salary.getYear());
		this.editSalaryFormData.setMonth(salary.getMonth());
		this.editSalaryFormData.setDay(salary.getDay());
		this.editSalaryFormData.setAmount(salary.getAmount());
		this.editSalaryFormData.setBankID(salary.getBankID());
	}
	
	@GetMapping("/bbtb/edit/salarydata")
	@ResponseBody
	public EditSalaryDataResponse edit_salary_data() {
		var salaryInfoOpt = salaryInfoService.searchSalaryByID(editSalaryDataRequest.getId());
		if(salaryInfoOpt.isEmpty()) {
			EditSalaryDataResponse responce = new EditSalaryDataResponse();
			return responce;
		}else {
			EditSalaryDataResponse responce = new EditSalaryDataResponse();
			responce.setName(salaryInfoOpt.get().getName());
			responce.setYear(salaryInfoOpt.get().getYear());
			responce.setMonth(salaryInfoOpt.get().getMonth());
			responce.setDay(salaryInfoOpt.get().getDay());
			responce.setAmount(salaryInfoOpt.get().getAmount());
			responce.setBankID(salaryInfoOpt.get().getBankID());
			return responce;
		}
	}
	
	//ポストでフォーム情報を取得
	@PostMapping("/bbtb/edit/salarydata")
	public void setEditSalaryData(@RequestBody EditSalaryDataRequest salary) {
		this.editSalaryDataRequest.setId(salary.getId());
	}
}
