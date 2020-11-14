package com.quote.configuration;

import java.time.LocalDateTime;
import javax.inject.Inject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.Transactional;
import com.quote.dao.Quote_Log_Repository;
import com.quote.exception.ExceptionHandler.QuoteException;
import com.quote.model.Quote_log;

@Configuration
@Transactional
public class LoggerImpl implements ILogger{
	
	@Autowired
	Quote_Log_Repository quote_Log_Repository;
	
	
	@Inject
	Quote_log quote_log_context;
	
	@Transactional
	public void logException(QuoteException ex) {
	Quote_log quote_log=Quote_log.builder()
			.created_date(LocalDateTime.now())
			.error_code(ex.getErrorCode())
			.message(ex.getDescription())
			.operation(quote_log_context.getOperation())
			.quote_id(quote_log_context.getQuote_id())
			.build();
	quote_Log_Repository.save(quote_log);	
	}

	
	
	@Transactional
	public void logToSqlAfterAction() {
		Quote_log quote_log=Quote_log.builder()
				.created_date(LocalDateTime.now())
				.operation(quote_log_context.getOperation())
				.quote_id(quote_log_context.getQuote_id())
				.build();
		quote_Log_Repository.save(quote_log);
		
	}
	
	

}
