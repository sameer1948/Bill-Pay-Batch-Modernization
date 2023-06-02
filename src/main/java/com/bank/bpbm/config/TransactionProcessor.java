package com.bank.bpbm.config;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.bank.bpbm.entity.Transaction;

@Component
public class TransactionProcessor implements ItemProcessor<Transaction, Transaction>{

	@Override
	public Transaction process(Transaction Transaction) throws Exception {
		return Transaction;
	}

}
