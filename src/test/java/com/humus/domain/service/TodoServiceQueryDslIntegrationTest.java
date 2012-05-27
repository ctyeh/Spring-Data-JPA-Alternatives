package com.humus.domain.service;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
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

import com.humus.domain.entity.Tag;
import com.humus.domain.entity.Todo;
import com.humus.domain.repo.TagRepository;

@ContextConfiguration(locations={"classpath:applicationContextTest.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class TodoServiceQueryDslIntegrationTest {
	
	
	
	@Autowired
	@Qualifier("TodoServiceQueryDsl")
	TodoService todoService;
	
	@Autowired
    private TagRepository tagRepository;

	
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
	
	
	
	
	@Transactional
	@Test
	public void searchByOneTag() {
		//Create tags
		Tag tag1 = new Tag("UI");
		Tag tag2 = new Tag("Service");
		Tag tag3 = new Tag("DB");
		
		Tag savedTag1 = tagRepository.save(tag1);
		Tag savedTag2 =tagRepository.save(tag2);
		Tag savedTag3 =tagRepository.save(tag3);
		
		
		long countTags = tagRepository.count();
		assertThat(countTags, is(3L));
		
		
		//Create Todo
		Todo todo1 = new Todo("Fix bug 001");
		todo1.getTags().add(savedTag1);
		savedTag1.getTodos().add(todo1); //Add tag `UI`
		
		
		Todo todo2 = new Todo("Fix bug 002");
		todo2.getTags().add(savedTag2);
		savedTag2.getTodos().add(todo2); //Add tag `Service`
		
		Todo todo3 = new Todo("Fix bug 003");
		todo3.getTags().add(savedTag2);
		savedTag2.getTodos().add(todo3); //Add tag `Service`
		
		Todo todo4 = new Todo("Fix bug 004");
		todo4.getTags().add(savedTag3);	//Add tag `DB`
		savedTag3.getTodos().add(todo4);
		
		todoService.add(todo1);
		todoService.add(todo2);
		todoService.add(todo3);
		todoService.add(todo4);
		
		long count = todoService.count();
		assertThat(count, is(4L));		
				
				
				
				
		Iterable<Todo> todos = todoService.searchByTag("Service");
		List<Todo> result = constructList(todos);

		System.out.println(">>>> searchByOneTag result="+result);
		
		
		assertThat(result.size(), is(2));		
	}
	
	
	
	
	
	
	@Transactional
	@Test
	public void searchByTags() {
		
		//Create tags
		Tag tag1 = new Tag("UI");
		Tag tag2 = new Tag("Service");
		Tag tag3 = new Tag("DB");
		
		Tag savedTag1 = tagRepository.save(tag1);
		Tag savedTag2 =tagRepository.save(tag2);
		Tag savedTag3 =tagRepository.save(tag3);
		
		
		long countTags = tagRepository.count();
		assertThat(countTags, is(3L));
		
		
		//Create Todo
		Todo todo1 = new Todo("Fix bug 001");
		todo1.getTags().add(savedTag1);
		savedTag1.getTodos().add(todo1);
		
		
		Todo todo2 = new Todo("Fix bug 002");
		todo2.getTags().add(savedTag2);
		savedTag2.getTodos().add(todo2);
		
		Todo todo3 = new Todo("Fix bug 003");
		todo3.getTags().add(savedTag2);
		savedTag2.getTodos().add(todo3);
		
		Todo todo4 = new Todo("Fix bug 004");
		todo4.getTags().add(savedTag3);
		savedTag3.getTodos().add(todo4);
		
		todoService.add(todo1);
		todoService.add(todo2);
		todoService.add(todo3);
		todoService.add(todo4);
		
		long count = todoService.count();
		assertThat(count, is(4L));		
		
		
		Iterable<Todo> todos = todoService.searchByTags(Arrays.asList(new String[]{"DB","UI"}));
		List<Todo> result = constructList(todos);

		System.out.println(">>>> result="+result);
		
		
		assertThat(result.size(), is(2));
		
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
