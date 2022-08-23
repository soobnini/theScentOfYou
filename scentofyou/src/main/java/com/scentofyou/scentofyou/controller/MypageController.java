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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.WebUtils;

import com.scentofyou.scentofyou.domain.User;
import com.scentofyou.scentofyou.domain.Perfume;
import com.scentofyou.scentofyou.domain.PerfumeLikes;
import com.scentofyou.scentofyou.domain.SearchText;
import com.scentofyou.scentofyou.service.UserRegistValidator;
import com.scentofyou.scentofyou.service.UserService;

@Controller
public class MypageController {
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRegistValidator validator;
	public void setValidator(UserRegistValidator validator) {
		this.validator = validator;
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
			return "thymeleaf/myPages/login";
		}
		
		model.addAttribute("userCommand", new UserCommand());
		return "thymeleaf/myPages/signUp";
	}
	
	@PostMapping("/scentofyou/updateProfile.do") 
	public ModelAndView register (HttpServletRequest request,HttpServletResponse response,
			@ModelAttribute("userCommand") UserCommand userCommand,
			BindingResult result) throws IOException {
		ModelAndView mav = new ModelAndView();
		
		validator.validateUserCommand(userCommand, result);
		if (result.hasErrors()) { // 오류 있는 경우
			mav.setViewName("thymeleaf/myPages/profile");//회원가입 폼으로
		}
		else { // 오류 없는 경우
			mav.setViewName("myPages/mypage");
			
			User userSession = 
					(User) WebUtils.getSessionAttribute(request, "userSession");
			
			User user = new User();
			user.setId(userSession.getId());//createUser시 ID가 있으면 자동으로 update
			user.setUserId(userSession.getUserId());//ID는 변경불가
			user.setUserPwd(userCommand.getPassword());
			user.setUserAge(userCommand.getAge());
			user.setUserName(userCommand.getName());
			user.setUserPhone(userCommand.getPhone());
			user.setUserGender(userCommand.getGender());
			try {
				userService.CreateUser(user);
			}
			catch(Exception e) {
				e.printStackTrace();
			}
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('프로필 수정 성공'); location.href='/scentofyou/mypage.do';</script>");
			out.flush();
		}
		
		return mav;
	}
}
