package com.bank.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entiryes.BankDetails;

@Repository
public interface BankDetailsRepository extends JpaRepository<BankDetails, Serializable> {

	public BankDetails findByBankId(Integer bankId);
}
