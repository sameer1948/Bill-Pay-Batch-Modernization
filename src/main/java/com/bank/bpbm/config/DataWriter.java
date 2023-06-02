package com.bank.bpbm.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bank.bpbm.dao.CustomerServices;
import com.bank.bpbm.dto.NewCustomerRequest;

@Component
public class DataWriter implements ItemWriter<NewCustomerRequest>{

	@Autowired
	private CustomerServices customerService;
	
	
	@Override
	public void write(List<? extends NewCustomerRequest> newCustomerRequest) throws Exception {
		System.out.println("New Request Sent");
		customerService.createNewCustomer(newCustomerRequest);
		System.out.println("New Request Completed");
		
	}
}


