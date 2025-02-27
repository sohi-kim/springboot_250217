package org.iclass.rest.controller;

import org.iclass.rest.dto.UserAccount;
import org.iclass.rest.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@RequiredArgsConstructor
@RestController
@Slf4j
public class UserAccountApiController {
	private final UserAccountService service;
	
	@GetMapping("/account/{userid}")
	public ResponseEntity<UserAccount> getOne(@PathVariable String userid){
		UserAccount account = service.userInfo(userid);
		return ResponseEntity.ok().body(account);
	}
}
