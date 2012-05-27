package com.humus.domain.repo;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.humus.domain.entity.QTodo;
import com.humus.domain.entity.Tag;
import com.mysema.query.types.expr.BooleanExpression;

public class TodoQueryDslPredicates {

	protected TodoQueryDslPredicates(){
	}
	
	
	
	
	
	static public BooleanExpression isNotComplete() {
        QTodo todo = QTodo.todo;
        
        return todo.isComplete.eq(Boolean.FALSE);
    }
	
	static public BooleanExpression isNotOverdueDate(){
		QTodo todo = QTodo.todo;
		
		long longUTC = Calendar.getInstance().getTimeInMillis();
	    
		return todo.dueDate.after(new Date(longUTC));
	}
	

	static public BooleanExpression search(String term){
		QTodo todo = QTodo.todo;

		return todo.title.contains(term);
	}
	
	
	
	
	
	
	static public BooleanExpression searchTag(Tag tag){
		QTodo todo = QTodo.todo;
		
		return todo.tags.contains(tag);
		
		
	}
	
	
	static public BooleanExpression searchTags(List<Tag> tags){
		QTodo todo = QTodo.todo;

		return todo.tags.any().in(tags);
	}
	
	
}
