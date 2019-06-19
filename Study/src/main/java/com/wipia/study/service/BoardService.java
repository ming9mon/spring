package com.wipia.study.service;

import java.util.List;

import com.wipia.study.domain.BoardVO;
import com.wipia.study.domain.Criteria;

public interface BoardService {
	
	// 글 목록 조회
	List<BoardVO> getBoardList(Criteria paging);
	
	// 글 상세 조회
	BoardVO getContent(BoardVO vo) throws Exception;
	
	// 글 등록
	void insertBoard(BoardVO vo);

	// 글 수정
	boolean updateBoard(BoardVO vo);

	// 글 삭제
	void deleteBoard(BoardVO vo);
	
	//글 갯수
	int totalCnt(Criteria cri);
	
}