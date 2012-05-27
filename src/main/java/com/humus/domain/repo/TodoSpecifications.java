package com.humus.domain.repo;

import java.sql.Date;
import java.util.Calendar;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.humus.domain.entity.Todo;
import com.humus.domain.entity.Todo_;

public class TodoSpecifications {

	public static Specification<Todo> isNotComplete() {
		return new Specification<Todo>() {

			@Override
			public Predicate toPredicate(Root<Todo> root,CriteriaQuery<?> query, CriteriaBuilder cb) {
				return cb.equal(root.get(Todo_.isComplete), Boolean.FALSE);
			}

		};
	}

	public static Specification<Todo> isNotOverdue() {
		return new Specification<Todo>() {

			@Override
			public Predicate toPredicate(Root<Todo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				long longUTC = Calendar.getInstance().getTimeInMillis();
				
				return cb.greaterThan(root.get(Todo_.dueDate), new Date(longUTC));
			}
		};
	}
	
	
	
	public static Specification<Todo> search(final String searchTerm) {
		return new Specification<Todo>() {
			@Override
			public Predicate toPredicate(Root<Todo> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				 String likePattern = getLikePattern(searchTerm);    
				 
				 return cb.like(cb.lower(root.<String>get(Todo_.title)), likePattern);
			}
			
			private String getLikePattern(final String searchTerm) {
                StringBuilder pattern = new StringBuilder();
                pattern.append("%");
                pattern.append(searchTerm.toLowerCase());
                pattern.append("%");
                return pattern.toString();
            }
		};
	}

}
