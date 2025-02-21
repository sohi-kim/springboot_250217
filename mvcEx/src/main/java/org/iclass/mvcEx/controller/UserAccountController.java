package org.iclass.mvcEx.controller;

import org.iclass.mvcEx.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
public class UserAccountController {
	private UserAccountService service;
	
	@GetMapping("/login")
	public String login() {
		return "login" ;   //login.html
	}
	
	@PostMapping("/login") 
	public String action() {
		
		return "redirect:/";
	}

}
