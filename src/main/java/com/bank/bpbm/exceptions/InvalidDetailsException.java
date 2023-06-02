package com.bank.bpbm.exceptions;

public class InvalidDetailsException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidDetailsException(String message) {
		super(message);
	}

}
