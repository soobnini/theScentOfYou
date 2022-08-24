package com.scentofyou.scentofyou.repository;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;

import com.scentofyou.scentofyou.domain.Perfume;
import com.scentofyou.scentofyou.domain.PerfumeLikes;
import com.scentofyou.scentofyou.domain.User;

public interface PerfumeRepository extends JpaRepository<Perfume, Integer> {
	List<Perfume> findTop3ByOrderByVotersDesc()throws DataAccessException;
}
