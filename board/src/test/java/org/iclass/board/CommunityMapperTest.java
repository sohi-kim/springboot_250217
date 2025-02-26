package org.iclass.board;

import static org.junit.jupiter.api.Assertions.*;

import org.iclass.board.dto.CommunityDTO;
import org.iclass.board.mapper.CommunityMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CommunityMapperTest {
	
	@Autowired		// 빈 저장소에서 자동주입(필드 주입)
	private CommunityMapper mapper;
	
	@Test
	@DisplayName("게시판 테이블의 399번 데이터 수정")
	void update() {
		CommunityDTO dto = CommunityDTO.builder()
				.title("Junit3")
				.content("[수정]테스트프레임웍크3")
				.idx(399)
				.build();
		int result = mapper.update(dto);
		CommunityDTO resDto = mapper.selectByIdx(dto.getIdx());
		Assertions.assertEquals(1, result);
		Assertions.assertEquals(dto.getContent(), resDto.getContent());
		
	}
	
	
	@Test   // 테스트할 메소드 표시
	@DisplayName("게시판 테이블에 insert 확인")
	@Disabled  // 다음에는 테스트에서 제외
	void test() {
		CommunityDTO dto = CommunityDTO.builder()
						.title("Junit")
						.content("테스트프레임웍크")
						.writer("twice")
						.build();
		int result = mapper.insert(dto);   //정상실행하면 1개행 추가. 값 1 리턴

		Assertions.assertEquals(1, result);
	}

}







