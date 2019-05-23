package com.wipia.study.service.impl;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Service;

import com.wipia.study.dao.SecessionDAO;
import com.wipia.study.domain.MemberVO;
import com.wipia.study.service.SecessionService;

@Service
public class SecessionServiceImpl implements SecessionService {

	@Inject
	SecessionDAO dao;
	
	@Override
	public int passCheck(MemberVO vo) {
		int result = dao.passCheck(vo);
		return result;
	}

	@Override
	public void secession(MemberVO vo,HttpSession session) {
		dao.secession(vo,session);
	}
	
}