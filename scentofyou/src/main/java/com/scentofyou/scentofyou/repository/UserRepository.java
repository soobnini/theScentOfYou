package com.scentofyou.scentofyou.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.CrudRepository;

import com.scentofyou.scentofyou.domain.User;
public interface UserRepository extends CrudRepository<User, Integer> {
	
	User findByUserId(String userId) throws DataAccessException;
	
	User findByUserIdAndUserPwd(String userId, String password) throws DataAccessException;
}
