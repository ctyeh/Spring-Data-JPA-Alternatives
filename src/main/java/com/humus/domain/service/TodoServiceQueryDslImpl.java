package com.humus.domain.service;

import static com.humus.domain.repo.TodoQueryDslPredicates.isNotComplete;
import static com.humus.domain.repo.TodoQueryDslPredicates.isNotOverdueDate;
import static com.humus.domain.repo.TodoQueryDslPredicates.search;
import static com.humus.domain.repo.TodoQueryDslPredicates.searchTag;
import static com.humus.domain.repo.TodoQueryDslPredicates.searchTags;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.humus.domain.entity.Tag;
import com.humus.domain.entity.Todo;
import com.humus.domain.repo.TagRepository;
import com.humus.domain.repo.TodoQueryDslRepository;


@Transactional(readOnly = true)
@Service("TodoServiceQueryDsl")
public class TodoServiceQueryDslImpl implements TodoService {

	
	
	@Autowired
    private TodoQueryDslRepository todoRepository;
	
    @Autowired
    private TagRepository tagRepository;
    
    /* 
     * For unit testing.
	 */
	public void setTodoRepository(TodoQueryDslRepository todoRepository) {
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
    
	
	/* (non-Javadoc)
	 * @see com.humus.domain.service.TodoService#findAllUncomplet()
	 */
	public Iterable<Todo> findAllUncomplet(){
		
		return todoRepository.findAll(isNotComplete());
	}
	
	
	/* (non-Javadoc)
	 * @see com.humus.domain.service.TodoService#findUnoverdueAndUncomplete()
	 */
	public Iterable<Todo> findUnoverdueAndUncomplete(){
		
		return todoRepository.findAll(isNotOverdueDate().and(isNotComplete()));
	}
	
	
	/*
	 * has Integration Test
	 * @see com.humus.domain.service.TodoServiceIntegrationTest#searchUncompleteUnoverdue()
	 */
	public Iterable<Todo> searchUncompleteUnoverdue(String term){
		
		return todoRepository.findAll( isNotOverdueDate().and(isNotComplete()).and(search(term)));
	}

	
	
	
	
	
	
	/*
	 * has Integration Test
	 * @see com.humus.domain.service.TodoServiceIntegrationTest#searchByOneTags()
	 */
	public Iterable<Todo> searchByTag(String tagName){
		List<Tag> tagList =tagRepository.findByName(tagName);
		
		if(tagList.isEmpty()){
			return null;
		}
		
		Tag tag = tagList.get(0);
		
				
		return todoRepository.findAll(searchTag(tag));
	}
	
	
	
	/*
	 * has Integration Test
	 * @see com.humus.domain.service.TodoServiceIntegrationTest#searchByOneTag()
	 */
	@Override
	public Iterable<Todo> searchByTags(List<String> tagNames) {
		List<Tag> tagList = new ArrayList<Tag>();
		
		for(String tagName: tagNames){
			tagList.addAll(tagRepository.findByName(tagName));
		}

		return todoRepository.findAll(searchTags(tagList));
	}

	
	
	
}
