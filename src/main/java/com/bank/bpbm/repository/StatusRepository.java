package com.bank.bpbm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.bpbm.entity.Status;

public interface StatusRepository extends JpaRepository<Status, String>{

	//@Query(nativeQuery = true, value = "SELECT * FROM  STATUS WHERE STATUS = :status")
	List<Status> findByStatus(String status);

}
