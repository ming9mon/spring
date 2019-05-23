package com.wipia.study.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.wipia.study.dao.SignUpDAO;
import com.wipia.study.domain.MemberVO;
import com.wipia.study.service.SignUpService;

@Service
public class SignUpServiceImpl implements SignUpService {

	@Autowired
	SignUpDAO dao;
	
	@Override
	public int idCheck(String userId) {
		
		int result = dao.idCheck(userId);
		return result;
	}

	@Override
	public void signUp(MemberVO vo) {
		dao.signUp(vo);
	}

}
