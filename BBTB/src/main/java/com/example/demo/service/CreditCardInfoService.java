package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.BankInfo;
import com.example.demo.entity.CreditCardInfo;
import com.example.demo.form.CreateCreditCardForm;
import com.example.demo.form.EditCreditCardForm;
import com.example.demo.repository.CreditCardInfoRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreditCardInfoService {
	
	/**クレカ情報レポジトリ*/
	private final CreditCardInfoRepository repository;
	
	public List<CreditCardInfo> searchCreditCards(List<BankInfo> banks){
		List<CreditCardInfo> creditCardsByBank = repository.findAll();
		List<CreditCardInfo> loginUserCreditCardInfo = new ArrayList<CreditCardInfo>();
		creditCardsByBank.forEach(card -> {
			banks.forEach(bank -> {
				if(card.getBankID() == bank.getId()) {
					loginUserCreditCardInfo.add(card);
				}
			});
		});
		return loginUserCreditCardInfo;
	}
	
	public Optional<CreditCardInfo> searchCreditCardByID(int creditCardID){
		Optional<CreditCardInfo> creditCardInfo = repository.findById(creditCardID);
		
		return creditCardInfo;
	}
	public List<CreditCardInfo> searchCreditCardsByBankID(int bankID){
		List<CreditCardInfo> creditCards = repository.findAll();
		List<CreditCardInfo> loginUserCreditCardByBankID = new ArrayList<CreditCardInfo>();
		creditCards.forEach(card -> {
			if(card.getBankID() == bankID) {
				loginUserCreditCardByBankID.add(card);
			}
		});
		return loginUserCreditCardByBankID;
	}
	public Optional<CreditCardInfo> resistCreditCardInfo(CreateCreditCardForm form){
		
		List<CreditCardInfo> loginUserCreditCardInfo = searchCreditCardsByBankID(form.getBankID());
		for(int i = 0; i < loginUserCreditCardInfo.size(); i++) {
			if(loginUserCreditCardInfo.get(i).getCredit_card_name().equals(form.getName())) {
				return Optional.empty();
			}
		}
		
		
		var creditCardInfo = new CreditCardInfo();
		creditCardInfo.setCredit_card_name(form.getName());
		creditCardInfo.setBankID(form.getBankID());
		
		return Optional.of(repository.save(creditCardInfo));
	}
	
	public Optional<CreditCardInfo> editCreditCardInfo(EditCreditCardForm form){
		List<CreditCardInfo> loginUserCreditCardInfo = searchCreditCardsByBankID(form.getBankID());
		for(int i = 0; i < loginUserCreditCardInfo.size(); i++) {
			if(loginUserCreditCardInfo.get(i).getCredit_card_name().equals(form.getName()) && loginUserCreditCardInfo.get(i).getId() != form.getId()) {
				return Optional.empty();
			}
		}
		
		var creditCardInfo = new CreditCardInfo();
		creditCardInfo.setId(form.getId());
		creditCardInfo.setCredit_card_name(form.getName());
		creditCardInfo.setBankID(form.getBankID());
		
		return Optional.of(repository.save(creditCardInfo));
	}
}
