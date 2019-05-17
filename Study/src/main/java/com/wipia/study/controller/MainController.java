package com.wipia.study.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class MainController {
	
	@RequestMapping(value = "/")
	public String home(Locale locale, Model model) {
		
		return "index";
	}
	
	
}
