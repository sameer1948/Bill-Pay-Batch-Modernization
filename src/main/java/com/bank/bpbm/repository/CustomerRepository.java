package com.bank.bpbm.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.bank.bpbm.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM  CUSTOMERS WHERE CUSTOMER_NAME = :customerName and GENDER = :gender and EMAIL = :email and PHONE = :phone and BRANCH = :branch ")
	List<Customer> validateCustomer(@Param("customerName") String customerName , @Param("gender") String gender , @Param("email") String email , @Param("phone") String phone, @Param("branch") String branch);
}
