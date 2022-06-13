package com.bank.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.BankDetailsDTO;
import com.bank.entiryes.BankDetails;
import com.bank.enu.Status;
import com.bank.repository.BankDetailsRepository;
import com.bank.service.BankDetailsService;
import com.bank.serviceimpl.BankDetailsServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
public class BankController {

	private final BankDetailsServiceImpl bankDetailsService;
	private final BankDetailsRepository bankDetailsRepository;

	@PostMapping(value = "/save/details", produces = "application/json", consumes = "application/json")
//	@PostMapping(value="/save/details")
	public ResponseEntity<?> saveBankDetails(@RequestBody BankDetailsDTO bankDetailsDTO) {

		this.bankDetailsService.addBankDetails(bankDetailsDTO);
		return ResponseEntity.ok(HttpStatus.CREATED);
	}

	@GetMapping(value = "/details/{bankId}")
	public ResponseEntity<?> getDetailsById(@PathVariable Integer bankId) {
		BankDetailsDTO bankDetails = this.bankDetailsService.fatchBankDetailsById(bankId);
		return ResponseEntity.ok(bankDetails);
	}

	@PutMapping(value = "/update/{bankId}")
	public ResponseEntity<?> updateBankDetails(@RequestBody BankDetailsDTO bankDetailsDTO,
			@PathVariable Integer bankId) {

		BankDetails details = this.bankDetailsRepository.findByBankId(bankId);

		if (bankDetailsDTO.getBranchStatus() == details.getBranchStatus()) {
			this.bankDetailsService.updateBankDetails(bankDetailsDTO, bankId);
			return ResponseEntity.ok(HttpStatus.UPGRADE_REQUIRED);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@DeleteMapping("/details/{bankId}")
	public ResponseEntity<?> deleteDetailsById(@PathVariable Integer bankId) {

		BankDetails bankDetails = this.bankDetailsRepository.findByBankId(bankId);

		if (bankDetails.getBranchStatus().equalsIgnoreCase("Active") && bankDetails.getBankId() == bankId) {
			String bankDetailsDeleted = this.bankDetailsService.deleteBankDetailsById(bankId);
			return ResponseEntity.ok(HttpStatus.ACCEPTED);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}

	@PutMapping("/edit/{bankId}")
	public ResponseEntity<?> updateBankDetailsWithStatus(@PathVariable Integer bankId) {
		BankDetails bd = null;
		Optional<BankDetails> details = this.bankDetailsRepository.findById(bankId);
		BankDetails bankDetails = details.get();

		if (bankDetails.getBranchStatus().equalsIgnoreCase("close")) {
			bankDetails.setBranchStatus("Active");
			this.bankDetailsRepository.save(bankDetails);
			return ResponseEntity.status(HttpStatus.OK).build();
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@GetMapping("/details")
	public List<BankDetails> getAllDetails(){
		List<BankDetails> bankDetails = this.bankDetailsService.fatchAllBankDetails();
		BankDetails bankDetails2 = bankDetails.get(0);
		System.out.println(bankDetails2);
		System.out.println(bankDetails);
		return bankDetails ;
	}
	

}
