package com.quote.exception;




import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.quote.configuration.ILogger;
import com.quote.dao.Quote_Log_Repository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	Quote_Log_Repository quote_Log_Repository;
	
	@Autowired
	ILogger loggerException;
	
	private static final Logger LOGGER =LoggerFactory.getLogger(ExceptionHandler.class);


	
	@org.springframework.web.bind.annotation.ExceptionHandler(QuoteNotFoundException.class)	
	protected ResponseEntity<QuoteException> handlerQuoteNotFoundExcep(QuoteNotFoundException quoteNotFoundException) {
		QuoteException ex = new QuoteException(1,"quote is not found","error");
	
		LOGGER.error(ex.getDescription(),quoteNotFoundException);
		loggerException.logException(ex);
		
		return new ResponseEntity<QuoteException>(ex,HttpStatus.NOT_FOUND);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(QuoteExistException.class)	
	protected ResponseEntity<QuoteException> handlerQuoteExistExcep(QuoteExistException quoteExistException) {
		QuoteException ex = new QuoteException(1,"quote is existx already","error");
		
		LOGGER.error(ex.getDescription(),quoteExistException);
		loggerException.logException(ex);
		
		
		return new ResponseEntity<QuoteException>(ex,HttpStatus.CONFLICT);
	}
	
	@org.springframework.web.bind.annotation.ExceptionHandler(NegativeArgumentException.class)	
	protected ResponseEntity<QuoteException> handlerNegativeArgumentExcep(NegativeArgumentException negativeException,HttpServletRequest request) {
		QuoteException ex = new QuoteException(1,"negativ","error");
		
		LOGGER.error(ex.getDescription(),negativeException);
		loggerException.logException(ex);

		
		return new ResponseEntity<QuoteException>(ex,HttpStatus.I_AM_A_TEAPOT);
	}
	
	
	

	@Getter
	@Setter
	@AllArgsConstructor
	public static class QuoteException {
		
		Integer errorCode;
		String description;
		String level;
	}

}
