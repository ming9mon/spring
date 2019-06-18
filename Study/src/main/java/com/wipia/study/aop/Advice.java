package com.wipia.study.aop;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class Advice {
	
	/*
	 * Before : 클래스의 메소드 실행 전
	 * within : BoardController 클래스를 지정
	 */
	@Before("within (com.wipia.study.controller.BoardController)")
	public void beforeAdvice() {
		System.out.println("BoardController Before");
	}
	
	/*
	 * After : 메소드 실행 후
	 * execution : getBoardList 메소드 지정 * 로 모든 메소드 지정 가능
	 * 접근지정자 : 생략 가능 ex) public, private
	 * * : 변환 타입
	 * 
	 */
	@After("execution(* com.wipia.study.controller.BoardController.getBoardList(..))")
	public void afterAdvice() {
		System.out.println("after getBoardList");
	}
	
	/*
	 * AfterThrowing : 예외 발생 시
	 * 메소드 호출 에러가 발생했을 때 동작
	 */
	@AfterThrowing(value="execution(* com.wipia.study.service.BoardService.getContent(..))", throwing="t")
	public void afterThrowingAdvice(Throwable t) {
		System.out.println(t);
	}
	
	@AfterThrowing(value="execution(* com.wipia.study.dao.BoardDAO.getContent(..))", throwing="t")
	public void dddd(Throwable t) {
		System.out.println("aa : "+t);
	}
	
}
