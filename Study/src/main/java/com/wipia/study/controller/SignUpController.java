package com.wipia.study.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wipia.study.domain.MemberVO;
import com.wipia.study.service.SignUpService;

@Controller
public class SignUpController {

	@Autowired
	SignUpService service;
	
	@RequestMapping(value ="/signUpPage.do")
	public String signUpPage(){
		
		return "signUp";
	}
	
	@RequestMapping(value = "/signUp.do")
	public String signUp(MemberVO vo) {
		
		
		
		return "login";
	}
	
	//produces ajax데이터 넘겨받을때 깨짐 방지
	@RequestMapping(value = "/idCheck.do",method = RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String idCheck(HttpServletRequest request) {
		
		String userId = request.getParameter("userId");
		int result=service.idCheck(userId);
		System.out.println("컨트롤러 리절트 : "+result);
		return Integer.toString(result);
	}
	
}
