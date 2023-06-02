package com.bank.bpbm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bpbm.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, String>{

}
