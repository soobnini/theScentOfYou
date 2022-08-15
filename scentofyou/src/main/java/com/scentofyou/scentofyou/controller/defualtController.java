package com.scentofyou.scentofyou.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scentofyou.scentofyou.service.UserService;

@Controller
public class defualtController {
	
	@Autowired
	private UserService userService;

  @GetMapping("/")
  public String index() {
	  String s = userService.getUser(1).getUserName();
    return "index";//dbConnection test용 . 추후 삭제예정
  }
  @GetMapping("/Main")
  public String Main() {
    return "thymeleaf/Main";
  }
}