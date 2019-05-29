package com.wipia.study.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipia.study.dao.BoardDAO;
import com.wipia.study.domain.BoardVO;
import com.wipia.study.domain.Criteria;
import com.wipia.study.service.BoardService;

@Service
public class BoardServiceImpl implements BoardService{

	@Autowired
	private BoardDAO boardDAO;
	
	@Override
	public List<BoardVO> getBoardList(Criteria paging) {
		return boardDAO.getBoardList(paging);
	}
	
	@Override
	public BoardVO getContent(BoardVO vo) {
		return boardDAO.getContent(vo);
	}

	@Override
	public void insertBoard(BoardVO vo) {
		boardDAO.insertBoard(vo);
	}

	@Override
	public boolean updateBoard(BoardVO vo) {
		return boardDAO.updateBoard(vo);
	}

	@Override
	public void deleteBoard(BoardVO vo) {
		boardDAO.deleteBoard(vo);
	}

	@Override
	public int totalCnt(Criteria cri) {
		return boardDAO.totalCnt(cri);
	}
	

}