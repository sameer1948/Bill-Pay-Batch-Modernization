package com.bank.bpbm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ACCOUNTS")
@SequenceGenerator(name="ACC_SEQ", sequenceName="ACC_SEQ", initialValue=50000, allocationSize=1)
public class Account {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACC_SEQ")
	@Column(name="ACCOUNT_NO")
	private Integer accountNo;
	
	@Column(name="CUSTOMER_ID")
	private Integer customerId;
	
	@Column(name="ACCOUNT_TYPE")
	private String accountType;
	
	@Column(name="ACCOUNT_BALANCE")
	private Double accountBalance;

	public Account() {
		super();
	}

	public Account(Integer accountNo, Integer customerId, String accountType, Double accountBalance) {
		super();
		this.accountNo = accountNo;
		this.customerId = customerId;
		this.accountType = accountType;
		this.accountBalance = accountBalance;
	}

	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Double getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}

	@Override
	public String toString() {
		return "Account [accountNo=" + accountNo + ", customerId=" + customerId + ", accountType=" + accountType
				+ ", accountBalance=" + accountBalance + "]";
	}
	
}
