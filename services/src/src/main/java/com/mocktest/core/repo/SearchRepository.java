package com.mocktest.core.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mocktest.core.entity.Search;

@Repository
public interface SearchRepository extends CrudRepository<Search, Long>{
	
}
