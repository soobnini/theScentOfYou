package com.scentofyou.scentofyou.controller;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.scentofyou.scentofyou.domain.User;
import com.scentofyou.scentofyou.domain.Perfume;
import com.scentofyou.scentofyou.domain.PerfumeLikes;
import com.scentofyou.scentofyou.domain.SearchText;
import com.scentofyou.scentofyou.service.PerfumeService;
import com.scentofyou.scentofyou.service.UserRegistValidator;
import com.scentofyou.scentofyou.service.UserService;

@Controller
public class MypageController {
	@Autowired
	private UserService userService;
	@Autowired
	private PerfumeService perfumeService;
	
	@Autowired
	private UserRegistValidator validator;
	public void setValidator(UserRegistValidator validator) {
		this.validator = validator;
	}
	
	@GetMapping("/scentofyou/favor.do")
	public ModelAndView showfavor (HttpServletRequest request) {
		User userSession = 
				(User) WebUtils.getSessionAttribute(request, "userSession");
		ModelAndView mav = new ModelAndView();
	
		if (userSession == null) { // 로그인이 안되어있는 경우
			mav.setViewName("thymeleaf/mypages/login");
		}
		else { // 로그인이 되어있는 경우
			User user = userService.getUser(userSession.getId());
			List<PerfumeLikes> LikesList = userService.getLikePerfumeList(user);
			mav.setViewName("thymeleaf/perfumes/favorList");
			mav.addObject("user", user);
			mav.addObject("LikeList",LikesList);
		}	
		
		return mav;
	}
	@RequestMapping("/scentofyou/deleteFavor/{perfumeId}.do")
	public String deleteFavor(@PathVariable int perfumeId) {
		try {
			Perfume perfume = perfumeService.getPerfume(perfumeId);
			userService.deleteFavor(perfume);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return "redirect:/scentofyou/favor.do";
	}
	
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
			List<SearchText> searchText = userService.getSerchTextList(user);
			List<PerfumeLikes> LikesList = userService.getLikePerfumeList(user);
			mav.setViewName("thymeleaf/myPages/myPage");
			mav.addObject("user", user);
			mav.addObject("searchTextList", searchText);
			mav.addObject("LikeList",LikesList);
		}	
		
		return mav;
	}
	/*프로필 수정*/
	@GetMapping("/scentofyou/updateProfile.do")
	public String showForm (HttpServletRequest request,Model model) {
		User userSession = 
				(User) WebUtils.getSessionAttribute(request, "userSession");
		if (userSession == null) { // 로그인이 안되어있는 경우
			return "thymeleaf/mypages/login";
		}
		
		model.addAttribute("userCommand", new UserCommand());
		return "thymeleaf/mypages/profile";
	}
	
	@PostMapping("/scentofyou/updateProfile.do") 
	public ModelAndView register (HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute("userCommand") UserCommand userCommand,
			BindingResult result) throws IOException {
		ModelAndView mav = new ModelAndView();
		System.out.println(userCommand);
		
			
			User userSession = 
					(User) WebUtils.getSessionAttribute(request, "userSession");
			
			User user = new User();
			user = userSession;
			user.setUserPwd(userCommand.getPassword());
			user.setUserPhone(userCommand.getPhone());
			try {
				userService.CreateUser(user);
			}
			catch(Exception e) {
				response.setContentType("text/html; charset=UTF-8");
				PrintWriter out = response.getWriter();
				e.printStackTrace();
				out.println("<script>alert('프로필 수정 실패'); location.href='/scentofyou/Main.do';</script>");
			}
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('프로필 수정 성공'); location.href='/scentofyou/Main.do';</script>");
			out.flush();
		
		return mav;
	}
}
