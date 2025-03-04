package org.iclass.board;

import static org.junit.jupiter.api.Assertions.*;

import org.iclass.board.dto.CommunityCommentDTO;
import org.iclass.board.mapper.CommunityCommentsMapper;
import org.iclass.board.service.CommunityCommentService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
class CommunityCommentServiceTest {
	
	@Autowired   //필드 주입
	CommunityCommentService service;
	
	@Autowired
	CommunityCommentsMapper mapper;
	
	@Test
	@DisplayName("520번 글의 댓글 목록 가져오기")
	void list() {
		log.info("520번 댓글  : {}", service.commentList(520));
		log.info("520번 댓글 갯수 : {}", service.commentList(520).size());
	}
	
	@Disabled
	@Test
	@DisplayName("510번 글의 댓글 추가하기")
	void regist() {
		CommunityCommentDTO dto = CommunityCommentDTO.builder()
				.writer("hognGD")
				.content("댓글 테스트")
				.mref(510)
				.build();
		int result = service.commentSave(dto);
		log.info("변경된 댓글 갯수 : {}",mapper.selectCommentCount(510));
		assertEquals(1, result);
	}
	
	//idx 26번 삭제
	@Test
	@DisplayName("메인글 510번의 댓글 26번 삭제하기")
	void remove() {
		int result = service.commentRemove(26,510);
		log.info("변경된 댓글 갯수 : {}",mapper.selectCommentCount(510));
		assertEquals(1, result);
	}
	
	
}












