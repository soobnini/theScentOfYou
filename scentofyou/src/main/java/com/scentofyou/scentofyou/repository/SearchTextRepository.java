package com.scentofyou.scentofyou.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.scentofyou.scentofyou.domain.User;
import com.scentofyou.scentofyou.domain.SearchText;

public interface SearchTextRepository extends CrudRepository<SearchText, Integer> {
	
	List<SearchText> findByUser(User user) throws DataAccessException;
}
