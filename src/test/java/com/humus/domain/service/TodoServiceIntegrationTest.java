package com.humus.domain.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.humus.domain.entity.Todo;

@ContextConfiguration(locations={"classpath:applicationContextTest.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TodoServiceIntegrationTest {
	
	
	
	@Autowired
	@Qualifier("TodoService")
	TodoService todoService;

	@Transactional
	@Test
	public void searchUncompleteUnoverdue() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DAY_OF_MONTH, 3);	//Every Todo should be not overdue.
		Date dueDate = new Date(cal.getTimeInMillis());
		
		Todo todo1 = new Todo("Fix bug 001");
		todo1.setIsComplete(Boolean.TRUE);
		todo1.setDueDate(dueDate);
		
		Todo todo2 = new Todo("Fix bug 002");
		todo2.setIsComplete(Boolean.FALSE); //This one is not complete.
		todo2.setDueDate(dueDate);
		
		Todo todo3 = new Todo("Fix bug 003");
		todo3.setIsComplete(Boolean.TRUE);
		todo3.setDueDate(dueDate);
		

		todoService.add(todo1);
		todoService.add(todo2);
		todoService.add(todo3);
		
		long count = todoService.count();
		assertThat(count, is(3L));
		
		
		Iterable<Todo> todos = todoService.searchUncompleteUnoverdue("bug");
		List<Todo> result = constructList(todos);

		assertThat(result.size(), is(1));
		
	}
	
	
	
	
	
	
	
	
	/**
	 * Private method
	 * @param persons
	 * @return
	 */
	private List<Todo> constructList(Iterable<Todo> persons) {
        List<Todo> list = new ArrayList<Todo>();
        for (Todo person: persons) {
            list.add(person);
        }
        return list;
    }
	

}
