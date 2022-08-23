package com.scentofyou.scentofyou.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scentofyou.scentofyou.domain.User;
import com.scentofyou.scentofyou.domain.Perfume;
import com.scentofyou.scentofyou.domain.PerfumeLikes;
import com.scentofyou.scentofyou.domain.SearchText;
import com.scentofyou.scentofyou.repository.PerfumeLikesRepository;
import com.scentofyou.scentofyou.repository.SearchTextRepository;
import com.scentofyou.scentofyou.repository.UserRepository;



@Service
public class UserService {
	@Autowired
	public UserRepository userRepository;
	@Autowired
	public SearchTextRepository searchTextRepository;
	@Autowired
	public PerfumeLikesRepository perfumeLikesRepository;
	public void setUserRepository(UserRepository userRepository, SearchTextRepository searchTextRepository) {
		this.userRepository = userRepository;
		this.searchTextRepository = searchTextRepository;
	}
	public User getUser(int id) {
		Optional<User> result = userRepository.findById(id);
		return result.get();
	}
	
	public User isUserExist(String id) {
		User result = userRepository.findByUserId(id);
		return result;
	}
	
	public User getSession(String userId, String password) {
		User result = userRepository.findByUserIdAndUserPwd(userId, password);
		return result;
	}
	
	@Transactional
	public int CreateUser(User user) {
		return userRepository.save(user).getId();// pk없으면 persistance 실행 후 id 반환(create)
	}
	
	public List<SearchText> getSerchTextList(User user){
		List<SearchText> result = null;
		try {
			result = searchTextRepository.findByUser(user);
		}
		catch(Exception e) {
			
		}
		return result;
	}
	
	public List<PerfumeLikes> getLikePerfumeList(User user){
		List<PerfumeLikes> result = null;
		try {
			result = perfumeLikesRepository.findAllByUser(user);
		}
		catch(Exception e) {
			
		}
		return result;
	}
}
