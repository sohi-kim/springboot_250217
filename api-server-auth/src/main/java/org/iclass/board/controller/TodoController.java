package org.iclass.board.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.ResponseDTO;
import org.iclass.board.dto.TodoDTO;
import org.iclass.board.model.Todo2Entity;
import org.iclass.board.service.TodoService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RequiredArgsConstructor
@RestController
@RequestMapping("/todo")
@Slf4j
public class TodoController {

	private final TodoService service;

	// 모든 요청이 @AuthenticationPrincipal 를 통해 인증된 사용자 정보를 가져온다.
	// JwtAuthenticationFilter 의 UsernamePasswordAuthenticationToken 객체에서 설정한 인증정보를 가져온다.
	// 가져온 객체는 SecurityContext::Authentication 이다.

	@PostMapping
	public ResponseEntity<?> createTodo(
			@AuthenticationPrincipal String userId,
			@RequestBody TodoDTO dto) {
		try {

			Todo2Entity entity = TodoDTO.toEntity(dto);

			// (1) 새로운 엔티티 등록하면 전체 목록을 리턴하는 서비스 실행
			List<Todo2Entity> entities = service.create(entity);

			// (2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
			List<TodoDTO> dtoList = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

			// (3) 변환된 TodoDTO 리스트를 이용해 ResponseDTO 를 초기화한다.
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtoList).build();

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			// (4)  예외가 나는 경우 dto 대신 error 에 메시지를 넣어 리턴한다.

			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}

	@GetMapping
	public ResponseEntity<?> retrieveTodoList(
			@AuthenticationPrincipal String userId
	) {
		// (1) 서비스 메서드의 retrieve 메서드를 사용해 로그인 사용자의 TodoEntity 리스트를 가져온다
		List<Todo2Entity> entities = service.retrieve(userId);

		// (2) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO 리스트로 변환한다.
		List<TodoDTO> dtoList = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

		// (3) 변환된 TodoDTO 리스트를 응답 객체 ResponseDTO 로 변환한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtoList).build();

		return ResponseEntity.ok().body(response);
	}


	@PutMapping
	public ResponseEntity<?> updateTodo(
			@AuthenticationPrincipal String userId,
			@RequestBody TodoDTO dto) {

		Todo2Entity entity = TodoDTO.toEntity(dto);
		List<Todo2Entity> entities = service.update(entity);

		// (1) 자바 스트림을 이용해 리턴된 엔티티 리스트를 TodoDTO리스트로 변환한다.
		List<TodoDTO> dtoList = entities.stream().map(TodoDTO::new).collect(Collectors.toList());

		// (2) 변환된 TodoDTO 리스트를 응답 객체 ResponseDTO 로 변환한다.
		ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtoList).build();

		return ResponseEntity.ok().body(response);
	}

	@DeleteMapping
	public ResponseEntity<?> deleteTodo(
			@AuthenticationPrincipal String userId,
			@RequestBody TodoDTO dto) {
		try {
			Todo2Entity entity = TodoDTO.toEntity(dto);
			List<Todo2Entity> entities = service.delete(entity);

			List<TodoDTO> dtos = entities.stream().map(TodoDTO::new).collect(Collectors.toList());
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().data(dtos).build();

			return ResponseEntity.ok().body(response);
		} catch (Exception e) {
			String error = e.getMessage();
			ResponseDTO<TodoDTO> response = ResponseDTO.<TodoDTO>builder().error(error).build();
			return ResponseEntity.badRequest().body(response);
		}
	}

}
