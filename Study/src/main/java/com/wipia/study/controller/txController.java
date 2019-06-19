package com.wipia.study.controller;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class txController {
	
	@Autowired
	SqlSessionTemplate sql;
	
	@GetMapping("/fxTest")
	@Transactional
	public String withdraw(String data) {
		
		if(data!=null) {
			System.out.println(data);
			sql.insert("t1Mapper.insertData",data);
			sql.insert("t2Mapper.insertData",data);
		}
		
		List<String> list1 = sql.selectList("t1Mapper.getData");
		List<String> list2 = sql.selectList("t2Mapper.getData");
		
		return list1.toString()+"<br>"+list2.toString();
	}
	
}
