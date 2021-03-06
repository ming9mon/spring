package com.wipia.study.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.wipia.study.domain.BoardVO;
import com.wipia.study.domain.PageMaker;
import com.wipia.study.domain.Criteria;
import com.wipia.study.domain.FileVO;
import com.wipia.study.service.BoardService;

@Controller
@SessionAttributes("board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	
	//글 목록
	@RequestMapping("/getBoardList.do")
	public String getBoardList(Criteria cri, Model model) {
		
		List<BoardVO> boardList = boardService.getBoardList(cri);
		
		int total = boardService.totalCnt(cri);
		// Model 정보 저장
		model.addAttribute("boardList",boardList);
		model.addAttribute("paging",new PageMaker(cri,total));
		return "boardList"; // View 이름 리턴
	}
	
	// 글 상세 조회
	@RequestMapping("/getContent.do")
	public String getBoard(BoardVO vo, Model model, @ModelAttribute("cri") Criteria cri) throws Exception{
		model.addAttribute("board", boardService.getContent(vo)); // Model 정보 저장
		return "content"; // View 이름 리턴
	}
	
	// 글 수정 페이지
	@RequestMapping("/modify.do")
	public String modify(BoardVO vo, Model model, @ModelAttribute("cri") Criteria cri) throws Exception{
		model.addAttribute("board", boardService.getContent(vo)); // Model 정보 저장
		return "modify"; // View 이름 리턴
	}
	
	// 글 쓰기
	@RequestMapping(value="/insertBoard.do", method=RequestMethod.POST) 
	public String insertBoard(BoardVO vo,MultipartFile[] uploadFile) throws IOException {
			boardService.insertBoard(vo,uploadFile); 
			
			return "redirect:getBoardList.do";
	}
	
	// 글 쓰기 페이지 이동
	@RequestMapping("/writeBoard.do") 
	public String moveInsertBoard()throws Exception{
		return "insertBoard";
	}
	 

	// 글 수정
	@RequestMapping("/updateBoard.do")
	public String updateBoard(@ModelAttribute("board") BoardVO vo,MultipartFile[] uploadFile , @ModelAttribute("cri") Criteria cri,RedirectAttributes rttr) {
		
		if(boardService.updateBoard(vo,uploadFile)) {
			rttr.addFlashAttribute("result","success");
		}
		
		rttr.addAttribute("amount",cri.getAmount());
		rttr.addAttribute("pageNum",cri.getPageNum());
		
		return "redirect:getBoardList.do";
	}

	// 글 삭제
	@RequestMapping("/deleteBoard.do")
	public String deleteBoard(BoardVO vo) {
		boardService.deleteBoard(vo);
		return "redirect:getBoardList.do";
	}
	
	//첨부파일 리스트 JSON으로 받기
	@GetMapping(value="/getAttachList", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@ResponseBody
	public ResponseEntity<List<FileVO>> getAttachList(Long bno){
		return new ResponseEntity<>(boardService.getAttachList(bno),HttpStatus.OK);
	}
	
	@GetMapping(value="/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	@ResponseBody
	public ResponseEntity<Resource> downloadFile(String fileName,String origin, @RequestHeader("User-Agent")String userAgent){
		Resource resource = new FileSystemResource("D:"+File.separator+"upload"+File.separator+fileName);
		//String resourceName = resource.getFilename();
		
		if(resource.exists() == false) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		try {
			
			String downloadName = null;
			
			//user-Agent의 정보를 파라미터로 받아 브라우저별 처리
			if(userAgent.contains("Trident")) {	//IE 브라우저 엔진 이름
				System.out.println("IE");
				downloadName = URLEncoder.encode(origin, "UTF-8").replaceAll("\\+"," ");
			}else if(userAgent.contains("Edge")) {
				System.out.println("Edge");
				downloadName = URLEncoder.encode(origin, "UTF-8");
			}else{	//크롬
				System.out.println("chrom");
				downloadName = new String(origin.getBytes("UTF-8"), "ISO-8859-1");
			}
			
			headers.add("Content-Disposition", "attachment; filename="+downloadName);
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity<Resource>(resource, headers, HttpStatus.OK);
	}
	
}