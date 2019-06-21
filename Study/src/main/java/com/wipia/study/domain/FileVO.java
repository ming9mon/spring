package com.wipia.study.domain;

import lombok.Data;

@Data
public class FileVO {
	private String fileName;
	private String uuid;
	private String path;
	private long bno;
}
