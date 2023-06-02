package com.bank.bpbm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bpbm.entity.CronJob;

public interface CronJobRepository extends JpaRepository<CronJob, Integer>{

}
