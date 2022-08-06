package com.scentofyou.scentofyou.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class defualtController {

  @GetMapping("/")
  public String index() {
    return "index";
  }
}