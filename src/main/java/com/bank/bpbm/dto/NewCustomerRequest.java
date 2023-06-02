package com.bank.bpbm.dto;

import com.bank.bpbm.entity.Account;
import com.bank.bpbm.entity.Customer;

public class NewCustomerRequest {
	
	private Customer customer;
	
	private Account account;

	public NewCustomerRequest() {
		super();
	}
	
	public NewCustomerRequest(Customer customer, Account account) {
		super();
		this.customer = customer;
		this.account = account;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "NewCustomerRequest [customer=" + customer + ", account=" + account + "]";
	}

}
