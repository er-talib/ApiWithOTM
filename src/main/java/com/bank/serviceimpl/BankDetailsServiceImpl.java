package com.bank.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.bank.dto.BankBranchDetailsDTO;
import com.bank.dto.BankDetailsDTO;
import com.bank.entiryes.BankBranchDetails;
import com.bank.entiryes.BankDetails;
import com.bank.enu.Status;
import com.bank.repository.BankBranchDetailsRepository;
import com.bank.repository.BankDetailsRepository;
import com.bank.service.BankDetailsService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BankDetailsServiceImpl implements BankDetailsService {

	private final BankDetailsRepository bankDetailsRepository;

	@Override
	public void addBankDetails(BankDetailsDTO bankDetailsDTO) {
		BankDetails bankDetails = new BankDetails();
		bankDetails.setBankName(bankDetailsDTO.getBankName());
		bankDetails.setBranchStatus(bankDetailsDTO.getBranchStatus());
		bankDetails.setBankAddress(bankDetailsDTO.getBankAddress());
		bankDetails.setFeadback(bankDetailsDTO.getFeadback());
		bankDetails.setMdName(bankDetailsDTO.getMdName());
		bankDetails.setTotalBankStaff(bankDetailsDTO.getTotalBankStaff());

		List<BankBranchDetails> bankBranchDetails = bankDetailsDTO.getBankBranchDetails().stream().map(details -> {
			BankBranchDetails bb = new BankBranchDetails();
			bb.setBankDetails(bankDetails);
			bb.setBranchAddress(details.getBranchAddress());
			bb.setBranchManager(details.getBranchManager());
			bb.setBranchName(details.getBranchName());
			return bb;
		}).collect(Collectors.toList());

		bankDetails.setBankBranchDetails(bankBranchDetails);
		this.bankDetailsRepository.save(bankDetails);

	}

	@Override
	public List<BankDetails> fatchAllBankDetails() {
		List<BankDetails> all = this.bankDetailsRepository.findAll();
	
		
		return all;
	}

	@Override
	public BankDetailsDTO fatchBankDetailsById(Integer bankId) {

		Optional<BankDetails> details = this.bankDetailsRepository.findById(bankId);
		if (details.isEmpty()) {
			System.out.println("Bank Details is not available");
		}
		BankDetails bd = details.get();
//	    System.out.println(bd);
		BankDetailsDTO bankDetails = new BankDetailsDTO();
		bankDetails.setBankName(bd.getBankName());
		bankDetails.setBranchStatus(bd.getBranchStatus());
		bankDetails.setBankAddress(bd.getBankAddress());
		bankDetails.setMdName(bd.getMdName());
		bankDetails.setTotalBankStaff(bd.getTotalBankStaff());
		bankDetails.setFeadback(bd.getFeadback());

		List<BankBranchDetailsDTO> list = bd.getBankBranchDetails().stream().map(branch -> {
			BankBranchDetailsDTO b = new BankBranchDetailsDTO();
			b.setBranchName(branch.getBranchName());
			b.setBranchManager(branch.getBranchManager());
			b.setBranchAddress(branch.getBranchAddress());
			b.setBankId(branch.getBankBranchId());
//			b.setBankDetails(bankDetails);
			return b;
		}).collect(Collectors.toList());

		bankDetails.setBankBranchDetails(list);
		System.out.println(bankDetails.toString());
		return bankDetails;
	}

	@Override
	public BankDetailsDTO updateBankDetails(BankDetailsDTO bankDetailsDTO, Integer bankId) {

		BankDetails bankDetails = new BankDetails();
		bankDetails.setBankId(bankId);
		bankDetails.setBankName(bankDetailsDTO.getBankName());
		bankDetails.setBranchStatus(bankDetailsDTO.getBranchStatus());
		bankDetails.setBankAddress(bankDetailsDTO.getBankAddress());
		bankDetails.setFeadback(bankDetailsDTO.getFeadback());
		bankDetails.setMdName(bankDetailsDTO.getMdName());
		bankDetails.setTotalBankStaff(bankDetailsDTO.getTotalBankStaff());

		List<BankBranchDetails> bankBranchDetails = bankDetailsDTO.getBankBranchDetails().stream().map(details -> {
			BankBranchDetails bb = new BankBranchDetails();
			bb.setBankBranchId(details.getBankId());
			bb.setBranchAddress(details.getBranchAddress());
			bb.setBranchManager(details.getBranchManager());
			bb.setBranchName(details.getBranchName());
			bb.setBankDetails(bankDetails);

			return bb;
		}).collect(Collectors.toList());

		bankDetails.setBankBranchDetails(bankBranchDetails);
		this.bankDetailsRepository.save(bankDetails);

		return null;
	}

	@Override
	public String deleteBankDetailsById(Integer bankId) {

		this.bankDetailsRepository.deleteById(bankId);
		return "Your details has been deleted";
	}

	

}
