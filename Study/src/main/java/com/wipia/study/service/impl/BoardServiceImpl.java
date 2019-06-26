package com.wipia.study.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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
		
		//게시글 디비에 저장
		boardDAO.insertBoard(vo);
		
		//게시한 글 번호 얻어오기
		long bno = boardDAO.getBno();
		
		//파일이 없으면 에러남
		if(!uploadFile[0].getOriginalFilename().equals("")) {
			//파일 저장및 list 세팅
			List<FileVO> list = setFileList(uploadFile, bno);
			//파일 디비에 추가
			boardDAO.insertAttach(list);	
		}
	}

	@Override
	@Transactional
	public boolean updateBoard(BoardVO vo, MultipartFile[] uploadFile) {
		
		List<FileVO> list = boardDAO.findByBno(vo.getIdx());
		
		//첨부된 파일이 없으면 게시글만 수정
		if(uploadFile[0].getOriginalFilename().equals("")) {
			return boardDAO.updateBoard(vo);
		}
		
		//기존에 첨부 파일이 있으면
		if(boardDAO.selectAttach(vo.getIdx())) {
			//db지우기
			boardDAO.delAttach(vo.getIdx());
			//파일 지우기
			deleteFiles(list);
		}
		
		//파일 저장및 list 세팅
		List<FileVO> saveList = setFileList(uploadFile, vo.getIdx());
		//파일 디비에 추가
		boardDAO.insertAttach(saveList);
		
		return boardDAO.updateBoard(vo);
	}

	@Override
	@Transactional
	public void deleteBoard(BoardVO vo) {
		List<FileVO> list = boardDAO.findByBno(vo.getIdx());
		if(list.size() > 0) {
			boardDAO.delAttach(vo.getIdx());	//db지우기
			deleteFiles(list);	//파일 지우기
		}
		boardDAO.deleteBoard(vo); //게시글 삭제
	}

	@Override
	public int totalCnt(Criteria cri) {
		return boardDAO.totalCnt(cri);
	}

	@Override
	public List<FileVO> getAttachList(Long bno) {
		return boardDAO.findByBno(bno);
	}
	
	
	
	
	private String getPath() {

		//날짜를 년-월-일로 포멧
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		//ex) 2019\\06\\21
		String path = str.replace("-",File.separator);
		
		return path;
	}
	
	private List<FileVO> setFileList(MultipartFile[] uploadFile,long bno){

		List<FileVO> list = new ArrayList<>();

		//파일을 저장할 경로 생성
		String path = getPath();
		
		//ex) D:\\upload\\2019\\06\\21
		File uploadPath = new File("D:"+File.separator+"upload"+File.separator+path);
		
		//폴더가 없다면 생성
		if (uploadPath.exists() == false) {
			uploadPath.mkdirs();
		}
		
		for(MultipartFile file : uploadFile) {
			FileVO fvo = new FileVO();
			String fileName = file.getOriginalFilename();
			
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
		
		return list;
	}
	
	private void deleteFiles(List<FileVO> list) {
		list.forEach(attach -> {
			try {
				Path file = Paths.get("D:\\upload\\"+attach.getPath()+"\\"+attach.getUuid());
				System.out.println(file);
				Files.deleteIfExists(file);
				
			}catch(Exception e) {
				System.out.println(e);
			}
		});//end forEach
	}
}