package com.humus.domain.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TodoList {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String category;

	
	
	public TodoList(String category) {
		super();
		this.category = category;
	}



	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}



	/**
	 * @param category the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	
	
	
	

}
