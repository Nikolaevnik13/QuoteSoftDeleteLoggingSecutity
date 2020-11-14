package com.quote.configuration;


import com.quote.exception.ExceptionHandler.QuoteException;





public interface ILogger {
	
	 void logException(QuoteException ex);
	 
	 void logToSqlAfterAction();

}
