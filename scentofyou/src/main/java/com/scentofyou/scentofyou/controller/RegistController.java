package com.scentofyou.scentofyou.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.scentofyou.scentofyou.domain.User;
import com.scentofyou.scentofyou.service.UserRegistValidator;
import com.scentofyou.scentofyou.service.UserService;
@Controller
public class RegistController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRegistValidator validator;
	public void setValidator(UserRegistValidator validator) {
		this.validator = validator;
	}

	@GetMapping("/scentofyou/register.do")
	public String showForm (Model model) {
		model.addAttribute("userCommand", new UserCommand());
		return "thymeleaf/myPages/signUp";
	}
	
	@PostMapping("/scentofyou/register.do") 
	public ModelAndView register (HttpServletResponse response,
			@ModelAttribute("userCommand") UserCommand userCommand,
			BindingResult result) throws IOException {
		ModelAndView mav = new ModelAndView();
		
		validator.validateUserCommand(userCommand, result);
		if (result.hasErrors()) { // 오류 있는 경우
			mav.setViewName("thymeleaf/myPages/signUp");//회원가입 폼으로
		}
		else { // 오류 없는 경우
			mav.setViewName("login");
			
			User user = new User();
			user.setId(0);
			user.setUserId(userCommand.getUserId());
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
			out.println("<script>alert('회원가입에 성공하였습니다'); location.href='/scentofyou/login.do';</script>");
			out.flush();
		}
		
		return mav;
	}
}
