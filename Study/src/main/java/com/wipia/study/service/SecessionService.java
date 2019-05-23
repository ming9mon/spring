package com.wipia.study.service;


import javax.servlet.http.HttpSession;

import com.wipia.study.domain.MemberVO;

public interface SecessionService {
	
	//패스워드 체크
	public int passCheck(MemberVO vo);
	
	//회원 탈퇴
	public void secession(MemberVO vo,HttpSession session);
}
