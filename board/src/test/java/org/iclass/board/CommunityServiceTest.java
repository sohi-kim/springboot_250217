package org.iclass.board;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.iclass.board.dto.CommunityDTO;
import org.iclass.board.dto.PageResponseDTO;
import org.iclass.board.mapper.CommunityMapper;
import org.iclass.board.service.CommunityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CommunityServiceTest {
	
	@Autowired private CommunityService service;
	
	/*
	@Mock
	private CommunityMapper mapper;
	
	@InjectMocks
	private CommunityService service;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	*/
	@Test
	void test() {
		
		PageResponseDTO pageList =
			service.getPageList(5);	
		
		log.info("pageList : {}",pageList);
		
	}

}
