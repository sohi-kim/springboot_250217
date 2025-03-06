package org.iclass.board.controller;

import org.iclass.board.dto.UserAccount;
import org.iclass.board.service.UserAccountService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Controller
public class UserAccountController {
	private final UserAccountService service;
		
	@GetMapping("login")
	public String login() {   // 로그인 화면 GET 요청
		return "login";    //login.html : form 과 input,button
	}
	
	@PostMapping("/login") 
	public String action(String userid, String password , 
			HttpSession	session	,RedirectAttributes reAttr) {
		
		UserAccount account = service.login(userid,password);
		log.info("login 계정 정보 : {} ", account);
		
		if(account != null) {
			session.setAttribute("username", account.getUserid());   
			// ★★★★★★★ 로그인기능-세션활용
			return "redirect:/";
		}else {  //로그인 실패
			reAttr.addFlashAttribute("fail", "y");
					// ㄴ login.html (화면) 으로 직접 전달하는 값
			return "redirect:login";
		}
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session,RedirectAttributes reAttr) {
		session.invalidate();
		reAttr.addFlashAttribute("message", "로그 아웃 했습니다.");
		return "redirect:/";    // context path 라고 부르고 화면은 index.html
	}
	

}
