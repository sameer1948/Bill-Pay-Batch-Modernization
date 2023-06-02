package com.bank.bpbm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bpbm.entity.Job;

public interface MyJobRepository extends JpaRepository<Job, Integer>{

}
