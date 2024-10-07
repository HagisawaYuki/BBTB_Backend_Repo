package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.SalaryInfo;
import com.example.demo.form.CreateSalaryForm;
import com.example.demo.form.EditSalaryForm;
import com.example.demo.repository.SalaryInfoRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class SalaryInfoService {
	
	private final SalaryInfoRepository repository;

	
	public Optional<SalaryInfo> searchSalaryByID(int id){
		Optional<SalaryInfo> salaryInfo = repository.findById(id);
		
		return salaryInfo;
	}
	
	public List<SalaryInfo> searchSalariesByBankID(int bankID){
		List<SalaryInfo> salaries = repository.findAll();
		List<SalaryInfo> loginUserSalaryByBankID = new ArrayList<SalaryInfo>();
		salaries.forEach(salary -> {
			if(salary.getBankID() == bankID) {
				loginUserSalaryByBankID.add(salary);
			}
		});
		return loginUserSalaryByBankID;
	}
	
	public Optional<SalaryInfo> resistSalary(CreateSalaryForm form){
		
		List<SalaryInfo> loginUserSalaries = searchSalariesByBankID(form.getBankID());
		for(int i = 0; i < loginUserSalaries.size(); i++) {
			if(loginUserSalaries.get(i).getYear() == form.getYear()
					&& loginUserSalaries.get(i).getMonth() == form.getMonth()
					&&  loginUserSalaries.get(i).getBankID() == form.getBankID()) {
				return Optional.empty();
			}
		}
		
		
		var salary = new SalaryInfo();
		salary.setName(form.getName());
		salary.setYear(form.getYear());
		salary.setMonth(form.getMonth());
		salary.setDay(form.getDay());
		salary.setBankID(form.getBankID());
		salary.setAmount(form.getAmount());
		
		return Optional.of(repository.save(salary));
	}
	
	public Optional<SalaryInfo> editSalary(EditSalaryForm form){
		List<SalaryInfo> loginUserSalary = searchSalariesByBankID(form.getBankID());
		for(int i = 0; i < loginUserSalary.size(); i++) {
			/**ここの条件はいずれ変更する*/
			if(loginUserSalary.get(i).getYear() == form.getYear() && loginUserSalary.get(i).getMonth() == form.getMonth() && loginUserSalary.get(i).getId() != form.getId()) {
				return Optional.empty();
			}
		}
		
		var salary = new SalaryInfo();
		salary.setId(form.getId());
		salary.setName(form.getName());
		salary.setYear(form.getYear());
		salary.setMonth(form.getMonth());
		salary.setDay(form.getDay());
		salary.setAmount(form.getAmount());
		salary.setBankID(form.getBankID());
		
		return Optional.of(repository.save(salary));
	}
}
