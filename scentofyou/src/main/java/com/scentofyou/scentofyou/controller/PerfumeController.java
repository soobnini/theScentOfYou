package com.scentofyou.scentofyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.scentofyou.scentofyou.domain.Perfume;
import com.scentofyou.scentofyou.service.PerfumeService;
import com.scentofyou.scentofyou.service.UserService;

@Controller
public class PerfumeController {
	@Autowired
	private UserService userService;
	@Autowired
	private PerfumeService perfumeService;
	
	@RequestMapping("/scentofyou/perfumeDetail/{perfumeId}.do")
	public String perfumeDetail(@PathVariable int perfumeId,Model model) {
		try {
			Perfume perfume = perfumeService.getPerfume(perfumeId);
			model.addAttribute("perfume", perfume);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "thymeleaf/perfumes/perfume";
	}
}
