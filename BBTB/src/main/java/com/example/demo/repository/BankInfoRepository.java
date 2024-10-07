package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.BankInfo;

public interface BankInfoRepository extends JpaRepository<BankInfo, Integer>{

}
