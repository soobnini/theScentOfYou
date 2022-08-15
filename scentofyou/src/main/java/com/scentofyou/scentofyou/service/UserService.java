package com.scentofyou.scentofyou.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scentofyou.scentofyou.domain.User;
import com.scentofyou.scentofyou.repository.UserRepository;



@Service
public class UserService {
	@Autowired
	public UserRepository userRepository;
	
	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	public User getUser(int id) {
		Optional<User> result = userRepository.findById(id);
		return result.get();
	}
	
	public User isUserExist(String id) {
		Optional<User> result = userRepository.findByUserId(id);
		return result.get();
	}
	
	public User getSession(String userId, String password) {
		User result = userRepository.findByUserIdAndUserPwd(userId, password);
		return result;
	}
	
	@Transactional
	public int CreateUser(User user) {
		return userRepository.save(user).getId();// pk없으면 persistance 실행 후 id 반환(create)
	}
}
