package com.humus.domain.service;

import static com.humus.domain.repo.TodoSpecifications.isNotComplete;
import static com.humus.domain.repo.TodoSpecifications.isNotOverdue;
import static com.humus.domain.repo.TodoSpecifications.search;
import static org.springframework.data.jpa.domain.Specifications.where;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.humus.domain.entity.Todo;
import com.humus.domain.repo.TodoSpecificationRepository;


@Transactional(readOnly = true)
@Service("TodoService")
public class TodoServiceSpecificationImpl implements TodoService {

	@Autowired
	private TodoSpecificationRepository todoRepository;
	
	
	
	
	public void setTodoRepository(TodoSpecificationRepository todoRepository) {
		this.todoRepository = todoRepository;
	}

	
	
	
	
	
	
	
	@Override
	public long count() {
		
		return this.todoRepository.count();
	}

	
	@Override
	public void add(Todo todo) {
		this.todoRepository.save(todo);
	}
	

	@Override
	public Iterable<Todo> findAllUncomplet() {
		
		return todoRepository.findAll(isNotComplete());
	}

	@Override
	public Iterable<Todo> findUnoverdueAndUncomplete() {
		
		return todoRepository.findAll(where(isNotComplete()).and(isNotOverdue()));
	}

	/*
	 * has Integration Test
	 * @see com.humus.domain.service.TodoServiceIntegrationTest#searchUncompleteUnoverdue()
	 */
	@Override
	public Iterable<Todo> searchUncompleteUnoverdue(String term) {
		
		return todoRepository.findAll(where(isNotComplete()).and(isNotOverdue()).and(search(term)));
				
	}


	
	
	
	
	@Override
	public Iterable<Todo> searchByTags(List<String> tagNames) {
		// TODO Auto-generated method stub
		return null;
	}

	
	@Override
	public Iterable<Todo> searchByTag(String tagName) {
		// TODO Auto-generated method stub
		return null;
	}


	

}
