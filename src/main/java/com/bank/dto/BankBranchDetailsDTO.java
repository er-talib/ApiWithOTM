package com.bank.dto;





import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@ToString
public class BankBranchDetailsDTO {
	

	private Integer bankId ;
	private String branchName ;
	private String branchAddress;
	private String branchManager ;
	
	

}
