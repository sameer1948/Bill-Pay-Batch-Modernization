package com.bank.bpbm.dto;


public class TransactionErrorReponse {

	private Integer accountNo;
	
	private String transactionType;
	
	private Double transactionAmount;
	
	private String errorMessage;

	public TransactionErrorReponse() {
		super();
	}
	
	public TransactionErrorReponse(Integer accountNo, String transactionType, Double transactionAmount,
			String errorMessage) {
		super();
		this.accountNo = accountNo;
		this.transactionType = transactionType;
		this.transactionAmount = transactionAmount;
		this.errorMessage = errorMessage;
	}
	
	public Integer getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(Double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "TransactionErrorReponse [accountNo=" + accountNo + ", transactionType=" + transactionType
				+ ", transactionAmount=" + transactionAmount + ", errorMessage=" + errorMessage + "]";
	}

}
