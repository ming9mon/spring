package com.wipia.study.controller;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wipia.study.domain.MemberVO;
import com.wipia.study.service.SecessionService;

@Controller
public class SecessionController {
	
	@Inject
	SecessionService service;
	
	//회원탈퇴 페이지
	@RequestMapping(value="secession.do")
	public String secession() {
		return "secession";
	}
	
	//회원탈퇴
	@RequestMapping(value="secessionProc.do")
	public String secessionProc(MemberVO vo,HttpSession session) {
		
		service.secession(vo,session);
		
		return "login";
	}
	
	//패스워드 체크
	@RequestMapping(value="passCheck.do", method=RequestMethod.POST, produces = "application/text; charset=utf8")
	@ResponseBody
	public String passCheck(MemberVO vo) {
		
		int result = service.passCheck(vo);
		return Integer.toString(result);
	}
}
