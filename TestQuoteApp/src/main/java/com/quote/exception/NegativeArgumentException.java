package com.quote.exception;

public class NegativeArgumentException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NegativeArgumentException() {
		super("argument is negative");
	}
}
