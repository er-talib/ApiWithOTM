package com.bank.entiryes;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "indian_bank_details")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class BankDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bankId ;
	@NotEmpty
	private String bankName ;
	@NotEmpty
	private String bankAddress ;
	@NotEmpty
	private String mdName ;
	private Integer totalBankStaff ;
	private String branchStatus ;
	@Length(min = 10 , max = 300)
	private String feadback ;
	@OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL, mappedBy = "bankDetails")
	private List<BankBranchDetails> bankBranchDetails;

}
