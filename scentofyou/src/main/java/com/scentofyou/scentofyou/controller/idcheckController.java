package com.scentofyou.scentofyou.controller;

import java.util.Map;
import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.scentofyou.scentofyou.domain.User;
import com.scentofyou.scentofyou.service.UserService;

@RestController
public class idcheckController {
	@Autowired
	private UserService userService;
	/*아이디 중복확인*/
	@PostMapping("/scentofyou/idCheck.do")
	public String idCheck (@RequestParam Map<String, Object> userId) {
		System.out.println("0");
		System.out.println(userId);
		String uid = (String)userId.get("userId");
		System.out.println(uid);
		User result = userService.isUserExist(uid);
		if(result == null) {
			return "false";
		}
		else {
			return "true";
		}
	}
}
