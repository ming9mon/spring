package com.wipia.study.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.wipia.study.domain.BoardVO;
import com.wipia.study.domain.Criteria;
import com.wipia.study.domain.FileVO;

@Repository
public class BoardDAO{
	
	@Autowired
	private SqlSessionTemplate mybatis;

	public void insertBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 insertBoard() 기능 처리");
		mybatis.insert("BoardMapper.insertBoard", vo);
	}

	public boolean updateBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 updateBoard() 기능 처리");
		return mybatis.update("BoardMapper.updateBoard", vo) == 1;
	}

	public void deleteBoard(BoardVO vo) {
		System.out.println("===> Mybatis로 deleteBoard() 기능 처리");
		mybatis.delete("BoardMapper.deleteBoard", vo);
	}

	public BoardVO getContent(BoardVO vo){
		System.out.println("===> Mybatis로 getContent() 기능 처리");
		return (BoardVO) mybatis.selectOne("BoardMapper.getContent", vo);
	}

	public List<BoardVO> getBoardList(Criteria paging) {
		System.out.println("===> Mybatis로 getBoardList() 기능 처리");
		return mybatis.selectList("BoardMapper.getBoardList",paging);
	}
	
	public int totalCnt(Criteria cri) {
		System.out.println("===> Mybatis로 totalCnt");
		return mybatis.selectOne("BoardMapper.getTotalCnt",cri);
	}

	public long getBno() {
		return mybatis.selectOne("BoardMapper.getBno");
	}

	public void insertAttach(List<FileVO> list) {
		mybatis.insert("BoardMapper.insertAttach",list);
	}
	
}