package com.scentofyou.scentofyou.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.scentofyou.scentofyou.domain.User;
import com.scentofyou.scentofyou.service.UserService;

@Controller
public class LoginController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/scentofyou/login.do")
	public String login () {
		return "login";
	}
	

	@PostMapping("/scentofyou/login.do")
	public ModelAndView handleRequest(
			HttpSession session,
			HttpServletResponse response,
			@RequestParam("id") String id,
			@RequestParam("password") String password,
			Model model) throws Exception {

		ModelAndView mav = new ModelAndView();
		User user = userService.getSession(id, password);;
		
		if (user == null) {
			mav.setViewName("login");

			response.setContentType("text/html;");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.println("<script>alert('로그인 정보가 일치하지 않습니다');</script>");
			out.println("<script>history.back();</script>");
			out.flush();
		}
		else {
			User userSession = new User();
			userSession = user;
			model.addAttribute("userSession", userSession);
			mav.setViewName("redirect:/Main.do");
		}
		session.setAttribute("user", user);
		return mav;
	}
	
	@GetMapping("/scentofyou/logout.do")
	public String handleRequest(HttpSession session) throws Exception {
		session.invalidate();
		return "redirect:/book.do";
	}
}
