package com.scentofyou.scentofyou.controller;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.scentofyou.scentofyou.domain.User;
import com.scentofyou.scentofyou.domain.SearchText;
import com.scentofyou.scentofyou.service.UserService;

@Controller
public class MypageController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/scentofyou/mypage.do")
	public ModelAndView showMypage (HttpServletRequest request) {
		User userSession = 
				(User) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();
	
		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("thymeleaf/myPages/login");
		}
		else { // 로그인이 되어있는 경우
			User user = userService.getUser(userSession.getId());
			List<SearchText> searchText = userService.getSerchTextList(user.getId());
			mav.setViewName("thymeleaf/myPages/myPage");
			mav.addObject("user", user);
			mav.addObject("searchText", searchText);
		}	
		
		return mav;
	}
}
