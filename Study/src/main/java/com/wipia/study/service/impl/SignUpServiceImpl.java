package com.wipia.study.service.impl;

import com.wipia.study.domain.MemberVO;
import com.wipia.study.service.SignUpService;

public class SignUpServiceImpl implements SignUpService {

	@Override
	public boolean idCheck(MemberVO vo) {
		
		return false;
	}

	@Override
	public void signUp(MemberVO vo) {
		
	}

}
