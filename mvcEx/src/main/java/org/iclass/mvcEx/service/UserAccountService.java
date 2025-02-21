package org.iclass.mvcEx.service;

import org.iclass.mvcEx.mapper.UserAccountMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class UserAccountService {
	private UserAccountMapper mapper;

}
