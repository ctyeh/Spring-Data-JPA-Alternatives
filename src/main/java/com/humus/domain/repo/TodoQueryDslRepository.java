package com.humus.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import com.humus.domain.entity.Todo;

public interface TodoQueryDslRepository extends JpaRepository<Todo, Long>, QueryDslPredicateExecutor<Todo> {

}
