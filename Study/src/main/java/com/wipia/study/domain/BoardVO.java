package com.wipia.study.domain;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Data
public class BoardVO {
	private int idx;
	private String title;
	private String writer;
	private String content;
	private Date regDate;
	private int cnt;
	
	private List<FileVO> fileList;
	
}
