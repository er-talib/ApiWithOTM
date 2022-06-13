package com.bank.service;

import java.util.List;
import java.util.Optional;

import com.bank.dto.BankDetailsDTO;
import com.bank.entiryes.BankDetails;

public interface BankDetailsService {
	
	public void addBankDetails(BankDetailsDTO bankDetailsDTO);
//	public List<BankDetailsDTO> fatchAllBankDetails();
	public List<BankDetails> fatchAllBankDetails();
	public  BankDetailsDTO fatchBankDetailsById(Integer bankId);
	public BankDetailsDTO updateBankDetails(BankDetailsDTO bankDetailsDTO, Integer bankId);
	public String deleteBankDetailsById(Integer bankId);

}
