package com.wipia.study.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SignUpDAO {
	
	@Autowired
	SqlSession sqlsession;
	
	//아이디 체크
	public int idCheck(String userId) {
		System.out.println("===> Mybatis로 idCheck");
		int result = sqlsession.selectOne("memberMapper.idCheck",userId);
		System.out.println(result);
		return result;
	}
	
	//회원가입
	
}
