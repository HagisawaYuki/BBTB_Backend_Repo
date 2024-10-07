package com.example.demo.response;

import java.util.List;

import com.example.demo.json.BankJson;

import lombok.Data;

@Data
public class BBTBInfoResponse {

	private List<BankJson> banks;
}
