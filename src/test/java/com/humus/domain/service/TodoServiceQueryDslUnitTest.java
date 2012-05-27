package com.humus.domain.service;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.humus.domain.entity.Todo;
import com.humus.domain.repo.TodoQueryDslRepository;
import com.mysema.query.types.Predicate;

public class TodoServiceQueryDslUnitTest {

	private TodoService todoService;

	private TodoQueryDslRepository todoRepositoryMock;

	@Before
	public void setUp() {
		todoService = new TodoServiceQueryDslImpl();

		todoRepositoryMock = mock(TodoQueryDslRepository.class);
		((TodoServiceQueryDslImpl)todoService).setTodoRepository(todoRepositoryMock);
	}

	
	@Test
    public void findAllUncomplet() {
		Iterable<Todo> expected = new ArrayList<Todo>();
        when(todoRepositoryMock.findAll(any(Predicate.class))).thenReturn(expected);
        
        Iterable<Todo> actual = todoService.findAllUncomplet();
        
        verify(todoRepositoryMock, times(1)).findAll(any(Predicate.class));
        verifyNoMoreInteractions(todoRepositoryMock);
              
        
        assertEquals(expected, actual);
	}
	
	
	@Test
    public void findUnoverdueDateAndUncomplete() {
		Iterable<Todo> expected = new ArrayList<Todo>();
        when(todoRepositoryMock.findAll(any(Predicate.class))).thenReturn(expected);
        
        Iterable<Todo> actual = todoService.findUnoverdueAndUncomplete();
        
        verify(todoRepositoryMock, times(1)).findAll(any(Predicate.class));
        verifyNoMoreInteractions(todoRepositoryMock);
              
        
        assertEquals(expected, actual);
		
	}
	
	
}
