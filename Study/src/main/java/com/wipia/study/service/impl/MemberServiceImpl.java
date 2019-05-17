package com.wipia.study.service.impl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.wipia.study.dao.MemberDAO;
import com.wipia.study.domain.MemberVO;
import com.wipia.study.service.MemberService;;

@Service
public class MemberServiceImpl implements MemberService {

	@Inject
	MemberDAO dao;
	
	@Override
	public boolean loginCheck(MemberVO vo,HttpSession session) {
		
		boolean result = dao.loginCheck(vo);
		if (result) {	//true 일경우 세션 등록
			MemberVO vo2 = memberInfo(vo);
			//세션 변수 등록
			session.setAttribute("userId",vo2.getUserId());
		}
		
		return result;
	}

	@Override
	public MemberVO memberInfo(MemberVO vo) {
		return dao.memberInfo(vo);
	}

	@Override
	public void logout(HttpSession session) {
		dao.logout(session);
	}
	
	
}
