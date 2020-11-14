package com.quote.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quote.model.Quote_log;

public interface Quote_Log_Repository extends JpaRepository<Quote_log, Integer>{

}
