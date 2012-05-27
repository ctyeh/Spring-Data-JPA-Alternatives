package com.humus.domain.service;

import java.util.List;

import com.humus.domain.entity.Todo;

public interface TodoService {

	public abstract long count();
	
	public abstract void add(Todo todo);

	public abstract Iterable<Todo> findAllUncomplet();

	public abstract Iterable<Todo> findUnoverdueAndUncomplete();

	public abstract Iterable<Todo> searchUncompleteUnoverdue(String term);
	
	
	public abstract Iterable<Todo> searchByTag(String tagName);
	
	public abstract Iterable<Todo> searchByTags(List<String> tagNames);

}