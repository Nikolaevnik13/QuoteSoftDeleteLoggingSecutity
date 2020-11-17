package com.quote.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quote.model.Operation;
import com.quote.model.Quote;
import com.quote.service.QuoteService;
import com.quote.model.Quote_log;

@RequestMapping("/quote")
@RestController
public class QuoteController {

	@Autowired
	QuoteService service;
	
	@Inject
	Quote_log quote_log_context;

	@PutMapping()
	public Quote addQuote(@RequestBody Quote quote,HttpServletRequest request) {
		quote_log_context.setOperation(Operation.CREATE);
		quote_log_context.setQuote_id(quote.getName());
		return service.addQuote(quote);
	}

	@GetMapping("/{name}")
	public Quote getQuote(@PathVariable String name) {
		return service.getQuote(name);
	}

	@PostMapping("/list")
	public List<Quote> getQuotesByNamesList(@RequestBody List<String> names) {
		return service.getQuotesByNames(names);
	}

	@GetMapping("/all")
	public List<Quote> getQuotes() {
		return service.getQuotes();
	}

	@DeleteMapping("/{name}")
	public Quote removeQuote(@PathVariable String name) {
		quote_log_context.setOperation(Operation.DELETED);
		quote_log_context.setQuote_id(name);
		return service.deleteQuote(name);
	}
	
	@PutMapping("/update/{name}")
	public Quote update(@RequestBody Quote quote,@PathVariable String name) {
		quote_log_context.setOperation(Operation.UPDATE);
		quote_log_context.setQuote_id(name);
		return service.updateQuote(quote,name);
	}

	

}
