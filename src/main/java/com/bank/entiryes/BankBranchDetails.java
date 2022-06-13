package com.bank.entiryes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "indian_bank_branch")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BankBranchDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bankBranchId;
	@NotEmpty
	private String branchName ;
	@NotEmpty
	private String branchAddress;
	@NotEmpty
	private String branchManager ;
	
	@ManyToOne
//	@JoinColumn(name ="fk_bank_id")
	private BankDetails bankDetails ;
	

}
