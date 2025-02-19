package org.iclass.webEx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AttributeTestController {
	// 요청 URL 에 경로(path) 를 추가 : 파일관리, URL 관리를 위함
	@GetMapping("/community/list")
	public String list() {
		
		return "community/list";  //추가된 경로 이름으로 폴더만들어서 지정
	}
	
	@GetMapping("/community/read")
	public String read() {
		
		return "community/read";
	}

}
