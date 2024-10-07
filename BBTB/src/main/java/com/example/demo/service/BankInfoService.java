package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.authentication.LoginUserDetails;
import com.example.demo.entity.BankInfo;
import com.example.demo.form.CreateBankForm;
import com.example.demo.form.EditBankForm;
import com.example.demo.repository.BankInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankInfoService {

	/**銀行情報レポジトリ*/
	private final BankInfoRepository repository;
	
	public List<BankInfo> searchBanks(){
		List<BankInfo> allBankInfo = repository.findAll();
		List<BankInfo> loginUserBankInfo = new ArrayList<BankInfo>();
		if(LoginUserDetails.getLoginUser() == null) {
			return loginUserBankInfo;
		}
		allBankInfo.forEach(bankInfo -> {
			if(bankInfo.getUserID().equals(LoginUserDetails.getLoginUser().getUserID())) {
				loginUserBankInfo.add(bankInfo);
			}
		});
		return loginUserBankInfo;
	}
	
	public Optional<BankInfo> searchBankByID(int id){
		Optional<BankInfo> bankInfo = repository.findById(id);
		
		return bankInfo;
	}
	
	public Optional<BankInfo> resistBankInfo(CreateBankForm form){
		
		List<BankInfo> loginUserBankInfo = searchBanks();
		for(int i = 0; i < loginUserBankInfo.size(); i++) {
			if(loginUserBankInfo.get(i).getName().equals(form.getName())) {
				return Optional.empty();
			}
		}
		
		var bankInfo = new BankInfo();
		bankInfo.setName(form.getName());
		bankInfo.setBank_name(form.getBank_name());
		bankInfo.setBalance(form.getBalance());
		bankInfo.setUserID(LoginUserDetails.getLoginUser().getUserID());
		
		return Optional.of(repository.save(bankInfo));
	}
	
	public Optional<BankInfo> editBankInfo(EditBankForm form){
		List<BankInfo> loginUserBankInfo = searchBanks();
		for(int i = 0; i < loginUserBankInfo.size(); i++) {
			if(loginUserBankInfo.get(i).getName().equals(form.getName()) && loginUserBankInfo.get(i).getId() != form.getId()) {
				return Optional.empty();
			}
		}
		
		var bankInfo = new BankInfo();
		bankInfo.setId(form.getId());
		bankInfo.setName(form.getName());
		bankInfo.setBank_name(form.getBank_name());
		bankInfo.setBalance(form.getBalance());
		bankInfo.setUserID(LoginUserDetails.getLoginUser().getUserID());
		
		return Optional.of(repository.save(bankInfo));
	}
}
