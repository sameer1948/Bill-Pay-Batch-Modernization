package com.bank.bpbm.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bank.bpbm.dao.CustomerServices;
import com.bank.bpbm.entity.Transaction;

@Component
public class TransactionWriter implements ItemWriter<Transaction>{

	@Autowired
	private CustomerServices customerService;
	
	@Override
	public void write(List<? extends Transaction> transactions) throws Exception {
		System.out.println("New Request Transaction Sent");
		
		for(Transaction transaction : transactions)
			if(transaction.getTransactionType().equalsIgnoreCase("WITHDRAW"))
				customerService.doDebit(transaction);
		
		System.out.println("New Request Transaction Completed");
		
	}

}
