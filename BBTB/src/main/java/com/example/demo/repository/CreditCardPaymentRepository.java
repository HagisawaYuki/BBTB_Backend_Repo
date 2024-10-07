package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CreditCardPayment;

public interface CreditCardPaymentRepository extends JpaRepository<CreditCardPayment, Integer> {

}
