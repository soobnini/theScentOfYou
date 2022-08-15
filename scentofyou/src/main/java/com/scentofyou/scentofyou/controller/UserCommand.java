package com.scentofyou.scentofyou.controller;

import java.io.Serializable;

import com.scentofyou.scentofyou.domain.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor 
@NoArgsConstructor
@SuppressWarnings("serial")
public class UserCommand implements Serializable {
	private String userId;
	private String password;
	private String passwordCheck;
	private String name;
	private int age;
	private String phone;
	private int gender;
	
}
