package com.humus.domain.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.humus.domain.entity.Todo;

public interface TodoSpecificationRepository extends JpaRepository<Todo, Long>, JpaSpecificationExecutor<Todo> {

}
