package com.humus.domain.predicates;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.util.Calendar;

import org.junit.Test;

import com.humus.domain.repo.TodoQueryDslPredicates;
import com.mysema.query.types.Predicate;



public class TodoQueryDslPredicatesTest {

	
    @Test
    public void search() {
        Predicate predicate =  TodoQueryDslPredicates.search("Foo");
        
        String predicateAsString = predicate.toString();
        
        //System.out.println(">>" + predicateAsString);

        assertEquals("contains(todo.title,Foo)", predicateAsString);
    }

    @Test
    public void unComplete() {
        Predicate predicate =  TodoQueryDslPredicates.isNotComplete();
        
        String predicateAsString = predicate.toString();
        
        //System.out.println(">>" + predicateAsString);

        assertEquals("todo.isComplete = false", predicateAsString);
    }

    @Test
    public void overdueDate() {
        Predicate predicate =  TodoQueryDslPredicates.isNotOverdueDate();
        
        String predicateAsString = predicate.toString();
        
        //System.out.println(">>" + predicateAsString);

        long longUTC = Calendar.getInstance().getTimeInMillis();
       
        assertEquals("todo.dueDate > "+ new Date(longUTC), predicateAsString);
    }
    
    
}
