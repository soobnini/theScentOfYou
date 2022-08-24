package com.scentofyou.scentofyou.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


import com.scentofyou.scentofyou.domain.Perfume;
import com.scentofyou.scentofyou.service.PerfumeService;


@Controller
public class SearchController {
	@GetMapping("/scentofyou/search.do")
	public String Move() {
		return "thymeleaf/perfumes/Search";
	}
	
	@RequestMapping("/jsp")
	public String jsp() throws Exception{
		return "searchDB";
	}
}

