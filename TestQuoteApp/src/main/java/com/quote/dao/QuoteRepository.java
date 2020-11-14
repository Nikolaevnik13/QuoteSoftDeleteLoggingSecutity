package com.quote.dao;



import org.springframework.data.jpa.repository.JpaRepository;


import com.quote.model.Quote;

public interface QuoteRepository extends JpaRepository<Quote,String>{


	
	


}
