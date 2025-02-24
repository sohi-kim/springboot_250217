package org.iclass.mvcEx.controller;

import org.iclass.mvcEx.dto.Gallery;
import org.iclass.mvcEx.service.GalleryUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Controller
public class GalleryUploadController {
	private GalleryUploadService service;
	
	@GetMapping("gallery")
	public String gallery() {
		
		return "gallery";
	}
	
	@PostMapping("gallery")
	public String gallery(Gallery dto) {
		service.uploadOne(dto);
		return "redirect:gallery";
	}
}





