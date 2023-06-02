package com.bank.bpbm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bpbm.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{

}
