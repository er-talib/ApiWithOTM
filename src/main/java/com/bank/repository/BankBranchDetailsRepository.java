package com.bank.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.entiryes.BankBranchDetails;

@Repository
public interface BankBranchDetailsRepository  extends JpaRepository<BankBranchDetails, Serializable> {

}
