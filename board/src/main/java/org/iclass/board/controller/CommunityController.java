package org.iclass.board.controller;

import java.util.List;

import org.iclass.board.dto.CommunityDTO;
import org.iclass.board.service.CommunityService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
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
		
		List<CommunityDTO> list = service.getPageList(1);
		model.addAttribute("list", list);
		return "community/list";		// community 폴더안에 list.html
	}
	
}
