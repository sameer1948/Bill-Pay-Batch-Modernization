package com.bank.bpbm.exceptions;

public class InSufficientFundsException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7669370047731028007L;

	public InSufficientFundsException(String message) {
		super(message);
	}

}
