package com.quote.service;

import java.util.List;

import com.quote.model.Quote;

public interface QuoteService {

	public Quote addQuote(Quote quote);
	
	Quote getQuote(String name);
	
	List<Quote> getQuotes();
	
	List<Quote> getQuotesByNames(List<String>names);
	
	Quote updateQuote (Quote quote,String quoteName);
	
	Quote deleteQuote(String name);
	
	
}
