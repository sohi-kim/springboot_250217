package org.iclass.mvcEx.controller;

import org.iclass.mvcEx.dto.UserAccount;
import org.iclass.mvcEx.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
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
	public String action(String userid, String password , 
			HttpSession	session	,RedirectAttributes reAttr) {
		
		UserAccount account = service.login(userid,password);
		log.info("login 계정 정보 : {} ", account);
		
		if(account != null) {
			session.setAttribute("account", account);
			return "redirect:/";
		}else {  //로그인 실패
			reAttr.addFlashAttribute("fail", "y");
					// ㄴ login.html (화면) 으로 직접 전달하는 값
			return "redirect:login";
		}
	}

}
