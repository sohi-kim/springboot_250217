package org.iclass.board.controller;

import java.time.LocalDate;

import org.iclass.board.dto.CommunityDTO;
import org.iclass.board.dto.PageResponseDTO;
import org.iclass.board.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Controller
public class CommunityController {
	private CommunityService service;
	
	@GetMapping("/community/list")
	public String list(@RequestParam(defaultValue = "1") int page, Model model) {
		
//		List<CommunityDTO> list = service.getPageList(page);
//		model.addAttribute("list", list);
		PageResponseDTO pageList = service.getPageList(page);
		model.addAttribute("pageList", pageList);
		model.addAttribute("page", page);	// 검색기능 구현하면 dto 로 작성
		log.info("오늘 날짜 : {}",LocalDate.now() );
		model.addAttribute("today", LocalDate.now());
		return "community/list";		// community 폴더안에 list.html
	}
	
	// 글 읽기
	@GetMapping("/community/read")
	public String read(int idx, int page, Model model) {
		log.info("idx : {} ,  page : {}",idx,page);
		// sql : idx 값으로 하나의 행 조회, mapper, service
		model.addAttribute("dto", service.read(idx));
		model.addAttribute("page", page);
		return "community/read";
	}
	
	// 글 쓰기
	@GetMapping("/community/write")
	public String write() {
		return "community/write";
	}
	
	@PostMapping("/community/write")
	public String write(CommunityDTO dto) {
		log.info("form 입력값 : {}",dto);
		service.write(dto);
		return "redirect:list";
	}
}
