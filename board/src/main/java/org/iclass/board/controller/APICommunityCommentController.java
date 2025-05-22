package org.iclass.board.controller;

import java.util.List;
import org.iclass.board.dto.CommunityCommentDTO;
import org.iclass.board.service.CommunityCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor   
@Slf4j
@RestController
public class APICommunityCommentController {
	private final CommunityCommentService service;
	
	
	//1. 댓글 리스트 요청
	@GetMapping("/api/comments/{mref}")
	public ResponseEntity<?> comments(@PathVariable int mref){
		List<CommunityCommentDTO> list = service.commentList(mref);
		return ResponseEntity.ok().body(list);
	}
	
	//2. 댓글 추가
	@PostMapping("/api/comments")
	public ResponseEntity<?> comments(@RequestBody CommunityCommentDTO dto,
			HttpServletRequest request){
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");		// 로그인 사용자
		log.info("Authorization : {}", request.getHeader("Authorization"));
		String requsername = request.getHeader("Authorization").split(" ")[0];  // 요청 사용자
		String sessionId = request.getHeader("Authorization").split(" ")[1];	// 요청 세션ID
		log.info("Authorization split: {} {}", requsername, sessionId);
		if(request.getHeader("Authorization") == null || 
				!session.getId().equals(sessionId) || 
				!requsername.equals(username)) {
			return ResponseEntity.badRequest().body("잘못된 접근입니다.");
		}
		int result = service.commentSave(dto);
		return ResponseEntity.ok().body(result);
	}
	//3. 댓글 삭제
	@DeleteMapping("/api/comments/{idx}")
	public ResponseEntity<?> comments(@PathVariable int idx,   //*idx 값만 사용하는 것으로 변경
			HttpServletRequest request){
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");       // 로그인 사용자
		log.info("Authorization : {}", request.getHeader("Authorization"));
		String requsername = request.getHeader("Authorization").split(" ")[0];	// 요청 사용자
		String sessionId = request.getHeader("Authorization").split(" ")[1];	// 요청 세션ID
		log.info("Authorization split: {} {}", requsername, sessionId);
		if(request.getHeader("Authorization") == null || 
				!session.getId().equals(sessionId)) {
			return ResponseEntity.badRequest().body("잘못된 접근입니다.");
		}else if(!service.getWriter(idx).equals(username)) {
			return ResponseEntity.badRequest().body("권한이 없습니다.");
		}	
		log.info("idx 파라미터 : {}", idx);
		int result = service.commentRemove(idx);    // 메소드 인자 1개로 변경
		return ResponseEntity.ok().body(result);
	}
	
}






