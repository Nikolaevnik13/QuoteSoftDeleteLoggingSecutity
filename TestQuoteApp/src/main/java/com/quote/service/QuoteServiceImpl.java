package com.quote.service;

import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.quote.configuration.ILogger;
import com.quote.dao.ItemRepository;
import com.quote.dao.QuoteRepository;
import com.quote.exception.QuoteExistException;
import com.quote.exception.QuoteNotFoundException;
import com.quote.model.Item;
import com.quote.model.Quote;




@Service
public class QuoteServiceImpl implements QuoteService{

	@Autowired
	QuoteRepository repository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@PersistenceContext
	EntityManager em ;	
	
	@Autowired
	ILogger myLogger;

	@Transactional
	@Override
	public Quote addQuote(Quote quote) {
	
		Quote quoteFromDB=repository.findById(quote.getName()).orElse(null);
		if (quoteFromDB!=null) {
			
			throw new QuoteExistException();
		}
		List<Item>listItems=quote.getItems();
		if (listItems!=null) {
			listItems.stream().map(i->itemRepository.findById(i.getId()).orElse(itemRepository.save(i)))
			.collect(Collectors.toList());
		}	
		Quote resQuote=repository.save(quote);
		myLogger.logToSqlAfterAction();
		return resQuote;
	}
	
	
	
	@Override
	public Quote getQuote(String name) {
	TypedQuery<Quote> query=em.createQuery("select q from Quote q where q.isDeleted=false and q.name=?1",Quote.class);
	query.setParameter(1, name);
	Quote quote;
	try {
		quote = query.getSingleResult();
	} catch (NoResultException  e) {
		
		throw new QuoteNotFoundException(e);
	}	
		return quote;
	}

	@Override
	public List<Quote> getQuotes() {	
		boolean isDeleted =false;
		Session session = em.unwrap(Session.class);
	    Filter filter =  session.enableFilter("deletedQuoteFilter");
	    filter.setParameter("isDeleted", isDeleted);
		List<Quote> quotes=repository.findAll();
	    session.disableFilter("deletedUserFilter");
		return quotes;
	}
	
	@Override
	public List<Quote> getQuotesByNames(List<String> names) {		
		return repository.findAllById(names);
	}
	
	@Transactional
	@Override
	public Quote updateQuote(Quote quote,String quoteName) {
		
		Quote quoteFromRepo=repository.findById(quoteName).orElseThrow(QuoteNotFoundException::new);
		if (quote.getPrice()!=null) {
			quoteFromRepo.setPrice(quote.getPrice());
		}
		if (quote.getItems()!=null) {
			quoteFromRepo.setItems(quote.getItems());
		}		
		myLogger.logToSqlAfterAction();
		return quoteFromRepo;
	}
	

	@Transactional
	@Override
	public Quote deleteQuote(String name) {
		Quote quote=repository.findById(name).orElseThrow(QuoteNotFoundException::new);
		repository.deleteById(name);
		myLogger.logToSqlAfterAction();
		return quote;
	}
	

}
