package com.newsfeedback.core.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.newsfeedback.core.entity.Search;

/**
 * @author 729706
 * Name: Ankur Bose
 * Date: Feb 28, 2019
 */
@Repository
public interface SearchRepository extends CrudRepository<Search, Long> {

}
