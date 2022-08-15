package com.scentofyou.scentofyou.service;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.scentofyou.scentofyou.controller.UserCommand;
import com.scentofyou.scentofyou.domain.User;

@Component
public class UserRegistValidator implements Validator {
	public boolean supports(Class<?> clazz) {
		return User.class.isAssignableFrom(clazz);
	}
	
	public void validate(Object obj, Errors errors) {
		validateUserCommand((UserCommand) obj, errors);
	}

	public void validateUserCommand(UserCommand userCommand, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userId", "ID_REQUIRED", "이메일은 필수 항목입니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "PASSWORD_REQUIRED", "비밀번호는 필수 항목입니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "passwordCheck", "PASSWORDCHECK_REQUIRED", "비밀번호 확인은 필수 항목입니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "NAME_REQUIRED", "이름은 필수 항목입니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "PHONE_REQUIRED", "전화번호는 필수 항목입니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "age", "AGE_REQUIRED", "나이는 필수 항목입니다");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "gender", "GENDER_REQUIRED", "성별은 필수 항목입니다");
		
		if (!userCommand.getPassword().equals(userCommand.getPasswordCheck())) {
			errors.reject("PASSWORD_MISMATCH", "비밀번호가 다릅니다. 비밀번호를 확인해주세요.");
			
		}
	}
}
