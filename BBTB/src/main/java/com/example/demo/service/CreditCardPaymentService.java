package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entity.CreditCardInfo;
import com.example.demo.entity.CreditCardPayment;
import com.example.demo.form.CreateCreditCardPaymentForm;
import com.example.demo.form.EditCreditCardPaymentForm;
import com.example.demo.repository.CreditCardPaymentRepository;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class CreditCardPaymentService {

	/**クレカ情報レポジトリ*/
	private final CreditCardPaymentRepository repository;
	
	public List<CreditCardPayment> searchCreditCardPayments(List<CreditCardInfo> cards){
		List<CreditCardPayment> creditCardsPayBycard = repository.findAll();
		List<CreditCardPayment> loginUserCreditCardpayment = new ArrayList<CreditCardPayment>();
		creditCardsPayBycard.forEach(payment -> {
			cards.forEach(card -> {
				if(payment.getCredit_cardID() == card.getId()) {
					loginUserCreditCardpayment.add(payment);
				}
			});
		});
		return loginUserCreditCardpayment;
	}
	public List<CreditCardPayment> searchCreditCardPaymentsByCardID(int creditCardID){
		List<CreditCardPayment> creditCardPayments = repository.findAll();
		List<CreditCardPayment> loginUserCreditCardPaymentByCreditCardID = new ArrayList<CreditCardPayment>();
		creditCardPayments.forEach(payment -> {
			if(payment.getCredit_cardID() == creditCardID) {
				loginUserCreditCardPaymentByCreditCardID.add(payment);
			}
		});
		return loginUserCreditCardPaymentByCreditCardID;
	}
	public Optional<CreditCardPayment> searchCreditCardPaymentByID(int creditCardPaymentID){
		Optional<CreditCardPayment> creditCardPaymentInfo = repository.findById(creditCardPaymentID);
		
		return creditCardPaymentInfo;
	}
	public Optional<CreditCardPayment> resistCreditCardPayment(CreateCreditCardPaymentForm form){
		
		List<CreditCardPayment> loginUserCreditCardPayment = searchCreditCardPaymentsByCardID(form.getCreditCardID());
		for(int i = 0; i < loginUserCreditCardPayment.size(); i++) {
			if(loginUserCreditCardPayment.get(i).getYear() == form.getYear()
					&& loginUserCreditCardPayment.get(i).getMonth() == form.getMonth()
					&&  loginUserCreditCardPayment.get(i).getCredit_cardID() == form.getCreditCardID()) {
				return Optional.empty();
			}
		}
		
		
		var creditCardPayment = new CreditCardPayment();
		creditCardPayment.setYear(form.getYear());
		creditCardPayment.setMonth(form.getMonth());
		creditCardPayment.setDay(form.getDay());
		creditCardPayment.setCredit_cardID(form.getCreditCardID());
		creditCardPayment.setAmount(form.getAmount());
		
		return Optional.of(repository.save(creditCardPayment));
	}
	
	public Optional<CreditCardPayment> editCreditCardPayment(EditCreditCardPaymentForm form){
		List<CreditCardPayment> loginUserCreditCardPayment = searchCreditCardPaymentsByCardID(form.getCreditCardID());
		for(int i = 0; i < loginUserCreditCardPayment.size(); i++) {
			if(loginUserCreditCardPayment.get(i).getYear() == form.getYear() && loginUserCreditCardPayment.get(i).getMonth() == form.getMonth() && loginUserCreditCardPayment.get(i).getId() != form.getId()) {
				return Optional.empty();
			}
		}
		
		var creditCardPayment = new CreditCardPayment();
		creditCardPayment.setId(form.getId());
		creditCardPayment.setYear(form.getYear());
		creditCardPayment.setMonth(form.getMonth());
		creditCardPayment.setDay(form.getDay());
		creditCardPayment.setAmount(form.getAmount());
		creditCardPayment.setCredit_cardID(form.getCreditCardID());
		
		return Optional.of(repository.save(creditCardPayment));
	}
	
	
	
}
