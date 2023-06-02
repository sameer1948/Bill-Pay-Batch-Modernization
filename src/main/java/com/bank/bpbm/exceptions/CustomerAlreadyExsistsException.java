package com.bank.bpbm.exceptions;

public class CustomerAlreadyExsistsException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8531227274911038847L;

	public CustomerAlreadyExsistsException(String message) {
		super(message);		
	}

}
