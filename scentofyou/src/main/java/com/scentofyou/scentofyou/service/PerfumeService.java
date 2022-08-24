package com.scentofyou.scentofyou.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scentofyou.scentofyou.domain.Perfume;
import com.scentofyou.scentofyou.domain.SearchText;
import com.scentofyou.scentofyou.domain.User;
import com.scentofyou.scentofyou.repository.PerfumeRepository;
import com.scentofyou.scentofyou.repository.SearchTextRepository;
import com.scentofyou.scentofyou.repository.UserRepository;

@Service
public class PerfumeService {
	@Autowired
	public PerfumeRepository perfumeRepository;
	
	public void setPerfumeRepository(PerfumeRepository perfumeRepository, SearchTextRepository searchTextRepository) {
		this.perfumeRepository = perfumeRepository;
	}
	public Perfume getPerfume(int id) {
		return perfumeRepository.getReferenceById(id);
	}
	public List<Perfume> getBest(){
		List<Perfume> result = null;
		try {
			result = perfumeRepository.findTop3ByOrderByVotersDesc();
			System.out.println(result);
		}
		catch(Exception e) {
			
		}
		return result;
	}
}
