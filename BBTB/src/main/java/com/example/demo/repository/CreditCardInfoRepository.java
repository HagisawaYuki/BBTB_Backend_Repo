package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.CreditCardInfo;

public interface CreditCardInfoRepository extends JpaRepository<CreditCardInfo, Integer> {
	
}
