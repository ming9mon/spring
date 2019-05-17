package com.wipia.study.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.wipia.study.domain.MemberVO;
import com.wipia.study.service.MemberService;

@Controller
@SessionAttributes("login")
public class LoginController {

	@Autowired
	private MemberService memberService;
	
	@RequestMapping("/login.do")
	public String login() {
		return "login";
	}
	
	//로그인 처리
	@RequestMapping(value="/loginCheck.do")
	public String loginCheck(@ModelAttribute MemberVO vo,HttpSession session) {
		
		boolean result = memberService.loginCheck(vo, session);
		ModelAndView mav = new ModelAndView();
		System.out.println("result : " + result);
		/*
		if(result==true) {
			mav.addObject("msg","success");
		}else {
			mav.addObject("msg","failure");
		}
		System.out.println("dd");
		
		*/
		return "index";
		
	}
	
}
