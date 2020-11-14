package com.quote.model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import lombok.ToString;


@ToString
@NoArgsConstructor
@Getter
@Entity
@EqualsAndHashCode(of = "name")
@Table(name = "quotes")
@SQLDelete(sql = "UPDATE quotes SET deleted=true WHERE name=?")
@FilterDef(name = "deletedQuoteFilter",parameters = @ParamDef(name="isDeleted",type = "boolean"))
@Filter(name = "deletedQuoteFilter",condition = "deleted = :isDeleted")

public class Quote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String name;
	private Integer price;
	@ManyToMany
	List<Item> items;
	@Column(name = "DELETED")	
	boolean isDeleted;

	
	public Quote(String name, Integer price, List<Item> items) {
		validateName(name);
		validatorPrice(price);
		this.name = name;
		this.price = price;
		this.items = items;
		isDeleted=false;
	}

	private void validatorPrice(Integer price) {
		if (price==null) {
			throw new IllegalArgumentException("value of price is not correct");
		}
		if (price < 0) {
			throw new IllegalArgumentException("value of price is not correct");
		}
	}
	
	public void setDeleted(boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	private void validateName(String name) {
		if (name==null) {
			System.out.println("ffffff");
			//TODO
		}
		if (name.isEmpty()) {
			throw new IllegalArgumentException();
		}
	}

	public void setName(String name) {
		validateName(name);
		this.name = name;
	}

	public void setPrice(Integer price) {
		validatorPrice(price);
		this.price = price;
		
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	

	@JsonIgnore
	public boolean isDeleted() {
		return isDeleted;
	}

	

	
}
