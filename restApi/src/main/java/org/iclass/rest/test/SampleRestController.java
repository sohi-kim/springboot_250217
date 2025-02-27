package org.iclass.rest.test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SampleRestController {
	//db 역할을 하는 list (테스트용)
	private List<SampleDTO> list;
	
	// 생성자에서 db 역할 데이터 추가 (테스트용)
	public SampleRestController() {
		list = new ArrayList<>();
		list.add(new SampleDTO("twice", "김땡땡", "1234"));
		list.add(new SampleDTO("wonder", "최원더", "2345"));
		list.add(new SampleDTO("hongGD", "홍길동", "3456"));
		list.add(new SampleDTO("KGC", "강감찬", "4567"));
	}
	
	@GetMapping("/samples/one")
	public ResponseEntity<SampleDTO> one(){
		return ResponseEntity.ok().body(list.get(2));
		// body 데이터는 자바 객체입니다. -> 스프링부트가 'json 문자열'로 변환하여 전송
	}
	
	@GetMapping("/samples/{index}")   // {index} 기호는 변수로 처리되는 경로 표시
	public ResponseEntity<?> one(@PathVariable  int index){
		// 응답 본문의 타입이 미결정이면 ?
		try {
			return ResponseEntity.ok().body(list.get(index));
		}catch (Exception e) {
			// index 값이 out of bound 이면 500 오류
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("서버오류");
		}
	}
	
	@PutMapping("/samples")
	public ResponseEntity<?> update(@RequestBody SampleDTO dto)	{
		log.info("update 할 dto :  {} ,{}, {}", 
				dto.getUserid(),dto.getUsername(),dto.getUserid());
		//update 역할
		list.add(0,dto);		//0번 인덱스 값을 수정
		
		return ResponseEntity.ok().body(1);
	}
	
	@DeleteMapping("/samples/{index}")
	public ResponseEntity<?> delete(@PathVariable int index){
		// delete 역할
		list.remove(index);
		return ResponseEntity.ok().body(1);
	}
	
	
	@PostMapping("/samples")
	public ResponseEntity<?> post(@RequestBody SampleDTO dto){
		log.info("insert 할 dto :  {} ,{}, {}", 
				dto.getUserid(),dto.getUsername(),dto.getUserid());
		try {
			//insert 역할
			list.add(dto);
			log.info("list 결과 : {}", list);
			return ResponseEntity.ok().body(1);    //정상 처리 되면 응답코드 200
			
		}catch (Exception e) {
			return ResponseEntity.badRequest().build();     //처리 중 오류 발생하면 
		}
		
	}
	
	
}
