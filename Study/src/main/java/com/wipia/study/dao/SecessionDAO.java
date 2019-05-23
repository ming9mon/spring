package com.wipia.study.dao;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.wipia.study.domain.MemberVO;

@Repository
public class SecessionDAO {
	
	@Inject
	SqlSession sqlsessoin;
	
	//패스워드 체크
	public int passCheck(MemberVO vo) {
		int result=sqlsessoin.selectOne("memberMapper.loginCheck",vo);

		return result;
	}
	
	//회원탈퇴
	public void secession(MemberVO vo,HttpSession session) {
		
		sqlsessoin.delete("memberMapper.secession",vo);
		//세션 삭제
		session.invalidate();
	}

	
}
