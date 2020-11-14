package com.quote.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quote.model.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {

}
