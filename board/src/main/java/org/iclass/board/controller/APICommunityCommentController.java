package org.iclass.board.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iclass.board.dto.CommunityCommentDTO;
import org.iclass.board.service.CommunityCommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor   // final 키워드 변수만 초기화하는 생성자
@Slf4j
@RestController
public class APICommunityCommentController {
	private final CommunityCommentService service;
	
	// 비동기 통신에서 session 사용
	@GetMapping("/api/me")
	public ResponseEntity<?> auth(HttpSession session){
		Map<String, String> map = new HashMap<>();
		map.put("sessionId", session.getId());
		map.put("username", (String)session.getAttribute("username"));
		log.info("sessionId : {}", map.get("sessionId"));
		log.info("username : {}", map.get("username"));
		return ResponseEntity.ok().body(map );
	}
	
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
		log.info("Authorization : {}", request.getHeader("Authorization"));
		if(request.getHeader("Authorization") == null || 
				!session.getId().equals(request.getHeader("Authorization"))) {
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
//		String username = (String)session.getAttribute("username");
		log.info("Authorization : {}", request.getHeader("Authorization"));
		String username = request.getHeader("Authorization").split(" ")[0];
		String sessionId = request.getHeader("Authorization").split(" ")[1];
		if(sessionId == null || 
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






