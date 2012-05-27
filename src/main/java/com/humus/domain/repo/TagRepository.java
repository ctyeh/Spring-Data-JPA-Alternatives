package com.humus.domain.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.humus.domain.entity.Tag;

public interface TagRepository extends JpaRepository<Tag, Long>{

	
	public List<Tag> findByName(String name);
	
}
