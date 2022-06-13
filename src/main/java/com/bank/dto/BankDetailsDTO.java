package com.bank.dto;

import java.util.List;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class BankDetailsDTO {
	
	private String bankName ;
	private String bankAddress ;
	private String mdName ;
	private Integer totalBankStaff ;
	private String branchStatus ;
	private String feadback ;
	private List<BankBranchDetailsDTO> bankBranchDetails;

}
