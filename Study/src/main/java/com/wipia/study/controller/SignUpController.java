package com.wipia.study.controller;



import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wipia.study.domain.MemberVO;

@Controller
public class SignUpController {

	@RequestMapping(value ="/signUpPage.do")
	public String signUpPage(){
		
		return "signUp";
	}
	
	@RequestMapping(value = "/signUp.do")
	public String signUp(MemberVO vo) {
		
		
		
		return "login";
	}
	
	@RequestMapping(value = "/idCheck.do",method = RequestMethod.POST)
	@ResponseBody
	public String idCheck(HttpServletRequest request) {
		
		String userId = request.getParameter("userId");
		System.out.println(userId);
		
		return userId;
	}
	
}
