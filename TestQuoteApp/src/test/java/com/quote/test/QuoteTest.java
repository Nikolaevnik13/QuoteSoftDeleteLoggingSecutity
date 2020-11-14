package com.quote.test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.quote.model.Item;
import com.quote.model.Quote;

class QuoteTest {
	Item item1;
	Item item2;
	Quote quote;
	List<Item>list;

	@BeforeEach
	void setUp() throws Exception {
		 item1=new Item(1, "item1");
		 item2=new Item(2, "item2");
		list=Arrays.asList(item1,item2);
		 quote =new Quote("q1",100, list);
		
	}



	@Test
	void testSetName() {
		quote.setName("test1");
		assertEquals(quote, new Quote("test1", 100, list));
		assertThatThrownBy(()->quote.setName("")).isInstanceOf(IllegalArgumentException.class);
		assertThatThrownBy(()->quote.setName(null)).isInstanceOf(NullPointerException.class);
	}

	@Test
	void testSetPrice() {
		assertThatThrownBy(()->quote.setPrice(-1)).isInstanceOf(IllegalArgumentException.class);
		quote.setPrice(500);
		assertEquals(quote.getPrice(),500);
	}
//	@Test
//	@Transactional
//	public void givenTransactional_whenCheckingForActiveTransaction_thenReceiveTrue() {
//	    assertTrue(TransactionSynchronizationManager.isActualTransactionActive());
//	}
//	@Test
//	public void givenNoTransactional_whenCheckingForActiveTransaction_thenReceiveFalse() {
//	    assertFalse(TransactionSynchronizationManager.isActualTransactionActive());
//	}

}
