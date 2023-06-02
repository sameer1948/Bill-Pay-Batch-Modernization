package com.bank.bpbm.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="TRANSACTIONS")
public class Transaction {
	
	@Id
	@Column(name="T_ID")
	@GenericGenerator(name = "trans_seq" , strategy = "com.bank.bpbm.utils.TransactionSequenceGenerator")
	@GeneratedValue(generator = "trans_seq")
	private String transactionId;

	@Column(name="T_TYPE")
	private String transactionType;
	
	@Column(name="T_TIMESTAMP")
	private String transactionTimestamp;
	
	@Column(name="ACCOUNT_NO")
	private Integer accountNo;
	
	@Column(name="CUSTOMER_ID")	
	private Integer customerId;

	@Column(name="BEFORE_Balance")
	private Double beforeBalance;
	
	@Column(name="T_AMOUNT")
	private Double transactionAmount;

	@Column(name="AFTER_Balance")
	private Double afterBalance;
	
	public Transaction() {
		super();
	}

	public Transaction(String transactionId, String transactionType, String transactionTimestamp, Integer accountNo,
			Integer customerId, Double beforeBalance, Double transactionAmount, Double afterBalance) {
		super();
		this.transactionId = transactionId;
		this.transactionType = transactionType;
		this.transactionTimestamp = transactionTimestamp;
		this.accountNo = accountNo;
		this.customerId = customerId;
		this.beforeBalance = beforeBalance;
		this.transactionAmount = transactionAmount;
		this.afterBalance = afterBalance;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getTransactionTimestamp() {
		return transactionTimestamp;
	}

	public void setTransactionTimestamp(String transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
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

	public Double getBeforeBalance() {
		return beforeBalance;
	}

	public void setBeforeBalance(Double beforeBalance) {
		this.beforeBalance = beforeBalance;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public Double getAfterBalance() {
		return afterBalance;
	}

	public void setAfterBalance(Double afterBalance) {
		this.afterBalance = afterBalance;
	}

	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", transactionType=" + transactionType
				+ ", transactionTimestamp=" + transactionTimestamp + ", accountNo=" + accountNo + ", customerId="
				+ customerId + ", beforeBalance=" + beforeBalance + ", transactionAmount=" + transactionAmount
				+ ", afterBalance=" + afterBalance + "]";
	}

}
