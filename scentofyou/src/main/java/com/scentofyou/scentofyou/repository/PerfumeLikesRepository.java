package com.scentofyou.scentofyou.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.scentofyou.scentofyou.domain.Perfume;
import com.scentofyou.scentofyou.domain.PerfumeLikes;
import com.scentofyou.scentofyou.domain.SearchText;
import com.scentofyou.scentofyou.domain.User;

public interface PerfumeLikesRepository extends JpaRepository<PerfumeLikes, Integer> {
	List<PerfumeLikes> findAllByUser(User user)throws DataAccessException;
}
