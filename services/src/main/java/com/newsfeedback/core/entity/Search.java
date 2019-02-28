package com.newsfeedback.core.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author 729706
 * Name: Ankur Bose
 * Date: Feb 28, 2019
 */
@Entity
public class Search {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String keyword;
	@CreationTimestamp
	private LocalDateTime searchTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public LocalDateTime getSearchTime() {
		return searchTime;
	}

	public void setSearchTime(LocalDateTime searchTime) {
		this.searchTime = searchTime;
	}
}
