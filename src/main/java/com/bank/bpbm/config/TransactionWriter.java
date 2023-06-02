package com.bank.bpbm.config;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bank.bpbm.dao.CustomerServices;
import com.bank.bpbm.entity.Transaction;
import com.bank.bpbm.utils.Constants;;

@Component
public class TransactionWriter implements ItemWriter<Transaction>{

	@Autowired
	private CustomerServices customerService;
	
	@Override
	public void write(List<? extends Transaction> transactions) throws Exception {
		System.out.println("New Request Transaction Sent");
		
//		for(Transaction transaction : transactions) {
//			
//			if(transaction.getTransactionType().equalsIgnoreCase("WITHDRAW"))
//				customerService.doDebit(transaction);
//		
//			else if(transaction.getTransactionType().equalsIgnoreCase("DEPOSIT"))
//				customerService.docredit(transaction);
//		}
		
		transactions.stream().forEach((transaction)->{
			if(transaction.getTransactionType().equalsIgnoreCase(Constants.WITHDRAW))
				customerService.doDebit(transaction);
		
			else if(transaction.getTransactionType().equalsIgnoreCase(Constants.DEPOSIT))
				customerService.docredit(transaction);
			
			else
				System.out.println("invalid Data");
		});
		
		System.out.println("New Request Transaction Completed");
		
	}

}
