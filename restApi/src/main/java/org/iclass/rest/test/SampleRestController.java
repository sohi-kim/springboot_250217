package org.iclass.rest.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SampleRestController {
	//db 역할을 하는 list
	private List<SampleDTO> list;
	
	// 생성자에서 db 역할 데이터 추가
	public SampleRestController() {
		list = new ArrayList<>();
		list.add(new SampleDTO("twice", "김땡땡", "1234"));
		list.add(new SampleDTO("wonder", "최원더", "2345"));
		list.add(new SampleDTO("hongGD", "홍길동", "3456"));
		list.add(new SampleDTO("KGC", "강감찬", "4567"));
	}
	
	
}
