package com.wipia.study.dao;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.wipia.study.domain.MemberVO;

@Repository
public class MemberDAO {

	@Inject
	SqlSession sqlSession;
	
	//로그인 체크
	public boolean loginCheck(MemberVO vo) {
		System.out.println("===> Mybatis로 loginCheck() 기능 처리");
		String name = sqlSession.selectOne("memberMapper.loginCheck",vo);
		return (name==null)?false:true;
	}
	
	//회원 정보
	public MemberVO memberInfo(MemberVO vo) {
		System.out.println("===> Mybatis로 memberInfo() 기능 처리");
		return sqlSession.selectOne("memberMapper.memberInfo",vo);
	}
	
	//로그 아웃
	public void logout(HttpSession session) {
		System.out.println("===> 로그아웃 기능 처리");
		session.invalidate();
	}
	
}
