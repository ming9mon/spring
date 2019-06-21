package com.wipia.study.service.impl;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.wipia.study.dao.BoardDAO;
import com.wipia.study.domain.BoardVO;
import com.wipia.study.domain.Criteria;
import com.wipia.study.domain.FileVO;
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
	public BoardVO getContent(BoardVO vo)  {
		return boardDAO.getContent(vo);
	}

	@Transactional
	@Override
	public void insertBoard(BoardVO vo, MultipartFile[] uploadFile) {
		List<FileVO> list = new ArrayList<>();
		
		//파일을 저장할 경로 생성
		String path = getPath();
		
		File uploadPath = new File(path);
		
		//폴더가 없다면 생성
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		//게시글 디비에 저장
		boardDAO.insertBoard(vo);
		
		//게시한 글 번호 얻어오기
		long bno = boardDAO.getBno();
		//파일이 없으면 에러남
		if(!uploadFile[0].getOriginalFilename().equals("")) {
			for(MultipartFile file : uploadFile) {
				FileVO fvo = new FileVO();
				String fileName = file.getOriginalFilename();
				System.out.println("name : "+file.getOriginalFilename());
				System.out.println("size : "+file.getSize());
				
				
				//파일명만 추출
				fileName = fileName.substring(fileName.lastIndexOf(File.separator)+1);
				//확장자 추출
				String ext=fileName.substring(fileName.lastIndexOf("."));
				//uuid 생성 
				String uuid= UUID.randomUUID().toString();
				
				//파일명을 UUID+확장자로 서버에 저장
				File saveFile = new File(uploadPath,uuid+ext);
				
				
				//vo 세팅
				fvo.setBno(bno);
				fvo.setFileName(fileName);
				fvo.setPath(path);
				fvo.setUuid(uuid+ext);
				
				//파일 저장
				try {
					file.transferTo(saveFile);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
				list.add(fvo);	//리스트에 추가
			}
			//파일 디비에 추가
			boardDAO.insertAttach(list);	
		}
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
	
	
	
	
	private String getPath() {

		//날짜를 년-월-일로 포멧
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		//ex) D:\\upload\\2019\\06\\21
		String path = "D:"+File.separator+"upload"+File.separator+str.replace("-",File.separator);
		
		return path;
	}
	
	

}