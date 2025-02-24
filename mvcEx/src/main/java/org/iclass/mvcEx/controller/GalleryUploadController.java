package org.iclass.mvcEx.controller;

import java.util.ArrayList;
import java.util.List;

import org.iclass.mvcEx.dto.Gallery;
import org.iclass.mvcEx.service.GalleryUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Controller
public class GalleryUploadController {
	private GalleryUploadService service;
	
	@GetMapping("galleries")
	public String galleries(Model model) {
		List<Gallery> list = new ArrayList<>();
		list.add(new Gallery("침실", "iclass", "침실1.jpg,침실2.jpg,침실3.jpg"));
		list.add(new Gallery("거실", "iclass", "거실1.jpg,거실2.jpg"));
		list.add(new Gallery("키친", "iclass", "키친1.jpg,키친2.jpg,키친3.jpg"));
		model.addAttribute("list", list);
		return "galleries";   // galleries.html
	}
	
	@PostMapping("galleries")
	public String galleries(Gallery dto) {
		service.uploadMany(dto);
		return "redirect:galleries";
	}
	
	
	
	@GetMapping("gallery")
	public String gallery(Model model) {
		// db를 사용하면 select 결과로 구현함.
		Gallery dto = Gallery.builder()
				.title("테스트1")
				.writer("iclass")
				.fileNames("bird.jpg")
				.build();
		model.addAttribute("dto", dto);
		return "gallery";
	}
	
	@PostMapping("gallery")
	public String gallery(Gallery dto) {
		log.info("POST gallery : {}",dto);
//		service.uploadOne(dto);
		service.uploadMany(dto);    // 1개 이상의 파일을 업로드
		return "redirect:gallery";
	}
}





