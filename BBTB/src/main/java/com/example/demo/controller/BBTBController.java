package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.authentication.LoginUserDetails;
import com.example.demo.entity.BankInfo;
import com.example.demo.entity.CreditCardInfo;
import com.example.demo.entity.CreditCardPayment;
import com.example.demo.entity.SalaryInfo;
import com.example.demo.json.BankJson;
import com.example.demo.json.CreditCardJson;
import com.example.demo.json.CreditCardPaymentJson;
import com.example.demo.json.SalaryJson;
import com.example.demo.request.BankClickRequest;
import com.example.demo.request.CreditCardClickRequest;
import com.example.demo.response.AuthPermitResponse;
import com.example.demo.response.BBTBInfoResponse;
import com.example.demo.response.BankInfoResponse;
import com.example.demo.response.CreditCardInfoResponse;
import com.example.demo.service.BankInfoService;
import com.example.demo.service.CreditCardInfoService;
import com.example.demo.service.CreditCardPaymentService;
import com.example.demo.service.SalaryInfoService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@Configuration
@RequiredArgsConstructor
public class BBTBController {
	
	
	private final BankInfoService bankService;
	private final CreditCardInfoService creditCardService;
	private final CreditCardPaymentService creditCardPaymentService;
	private final SalaryInfoService salaryInfoService;
	private BankClickRequest bankClickRequest = new BankClickRequest();
	private CreditCardClickRequest creditCardClickRequest = new CreditCardClickRequest();

	/**
	 * 認証ユーザの情報を返す
	 * @return
	 */
	@GetMapping("/bbtb")
	@ResponseBody
	public AuthPermitResponse getAuthPermit() {
		AuthPermitResponse responce = new AuthPermitResponse();
		responce.setIsLogin(LoginUserDetails.getIsLogin());
		return responce;
	}
	
	@GetMapping("/bbtb/home")
	@ResponseBody
	public BBTBInfoResponse getBBTBInfo() {
		BBTBInfoResponse response = new BBTBInfoResponse();
		List<BankJson> banksJson = new ArrayList<BankJson>();
		//ログインユーザの銀行情報をリスト化
		List<BankInfo> banks = bankService.searchBanks();
		if(banks.size() == 0) {
			return response;
		}
		//銀行ごとにループしてクレカ情報を取得
		banks.forEach(bank -> {
			BankJson bankJson = new BankJson();
			bankJson.setId(bank.getId());
			bankJson.setName(bank.getName());
			bankJson.setBank_name(bank.getBank_name());
			bankJson.setBalance(bank.getBalance());
			List<CreditCardInfo> creditCards = creditCardService.searchCreditCardsByBankID(bank.getId());
			List<CreditCardJson> creditCardsJson = new ArrayList<CreditCardJson>();
			creditCards.forEach(card -> {
				CreditCardJson creditCardJson = new CreditCardJson();
				creditCardJson.setId(card.getId());
				creditCardJson.setName(card.getCredit_card_name());
				List<CreditCardPayment> creditCardPayments = creditCardPaymentService.searchCreditCardPaymentsByCardID(card.getId());
				List<CreditCardPaymentJson> creditCardPaymentsJson = new ArrayList<CreditCardPaymentJson>();
				creditCardPayments.forEach(payments -> {
					CreditCardPaymentJson creditCardPaymentJson = new CreditCardPaymentJson();
					creditCardPaymentJson.setId(payments.getId());
					creditCardPaymentJson.setYear(payments.getYear());
					creditCardPaymentJson.setMonth(payments.getMonth());
					creditCardPaymentJson.setDay(payments.getDay());
					creditCardPaymentJson.setAmount(payments.getAmount());
					creditCardPaymentsJson.add(creditCardPaymentJson);
				});
				creditCardJson.setCredit_card_payments(creditCardPaymentsJson);
				creditCardsJson.add(creditCardJson);
			});
			bankJson.setCredit_cards(creditCardsJson);
			List<SalaryInfo> salaries = salaryInfoService.searchSalariesByBankID(bank.getId());
			List<SalaryJson> salariesJson = new ArrayList<SalaryJson>();
			salaries.forEach(salary -> {
				SalaryJson salaryJson = new SalaryJson();
				salaryJson.setId(salary.getId());
				salaryJson.setName(salary.getName());
				salaryJson.setYear(salary.getYear());
				salaryJson.setMonth(salary.getMonth());
				salaryJson.setDay(salary.getDay());
				salaryJson.setAmount(salary.getAmount());
				
				salariesJson.add(salaryJson);
			});
			bankJson.setSalaries(salariesJson);
			banksJson.add(bankJson);
		});
		response.setBanks(banksJson);
		return response;
	}
	
	@GetMapping("/bbtb/home/bank")
	@ResponseBody
	public BankInfoResponse getBankInfo() {
		
		Optional<BankInfo> bankOpt = bankService.searchBankByID(bankClickRequest.getClickBankID());
		if(bankOpt.isEmpty()) {
			BankInfoResponse response = new BankInfoResponse();
			return response;
		}
		BankInfo bank = bankOpt.get();
		BankInfoResponse response = new BankInfoResponse();
		response.setId(bank.getId());
		response.setName(bank.getName());
		response.setBank_name(bank.getBank_name());
		response.setBalance(bank.getBalance());
		List<CreditCardInfo> creditCards = creditCardService.searchCreditCardsByBankID(bank.getId());
		List<CreditCardJson> creditCardsJson = new ArrayList<CreditCardJson>();
		creditCards.forEach(card -> {
			CreditCardJson creditCardJson = new CreditCardJson();
			creditCardJson.setId(card.getId());
			creditCardJson.setName(card.getCredit_card_name());
			List<CreditCardPayment> creditCardPayments = creditCardPaymentService.searchCreditCardPaymentsByCardID(card.getId());
			List<CreditCardPaymentJson> creditCardPaymentsJson = new ArrayList<CreditCardPaymentJson>();
			creditCardPayments.forEach(payments -> {
				CreditCardPaymentJson creditCardPaymentJson = new CreditCardPaymentJson();
				creditCardPaymentJson.setId(payments.getId());
				creditCardPaymentJson.setYear(payments.getYear());
				creditCardPaymentJson.setMonth(payments.getMonth());
				creditCardPaymentJson.setDay(payments.getDay());
				creditCardPaymentJson.setAmount(payments.getAmount());
				creditCardPaymentsJson.add(creditCardPaymentJson);
			});
			creditCardJson.setCredit_card_payments(creditCardPaymentsJson);
			creditCardsJson.add(creditCardJson);
		});
		response.setCredit_cards(creditCardsJson);
		List<SalaryInfo> salaries = salaryInfoService.searchSalariesByBankID(bank.getId());
		List<SalaryJson> salariesJson = new ArrayList<SalaryJson>();
		salaries.forEach(salary -> {
			SalaryJson salaryJson = new SalaryJson();
			salaryJson.setId(salary.getId());
			salaryJson.setName(salary.getName());
			salaryJson.setYear(salary.getYear());
			salaryJson.setMonth(salary.getMonth());
			salaryJson.setDay(salary.getDay());
			salaryJson.setAmount(salary.getAmount());
			
			salariesJson.add(salaryJson);
		});
		response.setSalaries(salariesJson);
		return response;
	}
	
	@PostMapping("/bbtb/home/bank")
	public void setBankID(@RequestBody BankClickRequest request) {
		bankClickRequest.setClickBankID(request.getClickBankID());
	}
	
	@GetMapping("/bbtb/home/credit_card")
	@ResponseBody
	public CreditCardInfoResponse getCreditCardInfo() {
		
		Optional<CreditCardInfo> cardOpt = creditCardService.searchCreditCardByID(creditCardClickRequest.getClickCreditCardID());
		if(cardOpt.isEmpty()) {
			CreditCardInfoResponse response = new CreditCardInfoResponse();
			return response;
		}
		
		CreditCardInfo creditCard = cardOpt.get();
		CreditCardInfoResponse response = new CreditCardInfoResponse();
		response.setId(creditCard.getId());
		response.setName(creditCard.getCredit_card_name());
		List<CreditCardPayment> creditCardPayments = creditCardPaymentService.searchCreditCardPaymentsByCardID(creditCard.getId());
		List<CreditCardPaymentJson> creditCardPaymentsJson = new ArrayList<CreditCardPaymentJson>();
		creditCardPayments.forEach(payments -> {
			CreditCardPaymentJson creditCardPaymentJson = new CreditCardPaymentJson();
			creditCardPaymentJson.setId(payments.getId());
			creditCardPaymentJson.setYear(payments.getYear());
			creditCardPaymentJson.setMonth(payments.getMonth());
			creditCardPaymentJson.setDay(payments.getDay());
			creditCardPaymentJson.setAmount(payments.getAmount());
			creditCardPaymentsJson.add(creditCardPaymentJson);
		});
		response.setCredit_card_payments(creditCardPaymentsJson);
		return response;
	}
	
	@PostMapping("/bbtb/home/credit_card")
	public void setCreditCardID(@RequestBody CreditCardClickRequest request) {
		creditCardClickRequest.setClickCreditCardID(request.getClickCreditCardID());
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

