package com.scentofyou.scentofyou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scentofyou.scentofyou.domain.Perfume;
import com.scentofyou.scentofyou.service.PerfumeService;
import com.scentofyou.scentofyou.service.UserService;

@Controller
public class defualtController {
	@Autowired
	PerfumeService perfumeService;
	
	@GetMapping("/scentofyou/Main.do")
	public String Main(Model model) {
		List<Perfume> perfumeList = perfumeService.getBest();
		model.addAttribute("bests",perfumeList);
		return "thymeleaf/main/main";
	}
	@GetMapping("/scentofyou/default.do")
	public String header() {
		return "thymeleaf/default";
	}
	@GetMapping("/scentofyou/graphGender.do")
	public String graphGender() {
		return "thymeleaf/perfumes/graphGender";
	}
	@GetMapping("/scentofyou/graphAge.do")
	public String graphAge() {
		return "thymeleaf/perfumes/graphAge";
	}
}