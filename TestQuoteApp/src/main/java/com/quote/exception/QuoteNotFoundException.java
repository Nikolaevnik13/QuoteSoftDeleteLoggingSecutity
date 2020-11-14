package com.quote.exception;



public class QuoteNotFoundException extends RuntimeException{
		/**
		 * 
		 */
	private static final long serialVersionUID = 1L;
	
	public QuoteNotFoundException(Throwable e) {
		super(e);

	}
	public QuoteNotFoundException() {
		

	}
}
