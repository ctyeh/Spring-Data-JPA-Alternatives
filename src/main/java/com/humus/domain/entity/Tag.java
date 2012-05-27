package com.humus.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Tag {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	
	@ManyToMany
	private List<Todo> todos;
	
	private String name;

	
	
	public Tag(String name){
		this.name = name;
		
		this.todos = new ArrayList<Todo>();
	}
	
	
	/**
	 * @return the todos
	 */
	public List<Todo> getTodos() {
		return todos;
	}


	/**
	 * @param todos the todos to set
	 */
	public void setTodos(List<Todo> todos) {
		this.todos = todos;
	}

	
	
	
	
	
	
	
	

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}



	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tag [name=").append(name).append("]");
		return builder.toString();
	}
	
	
	
	
	
}
