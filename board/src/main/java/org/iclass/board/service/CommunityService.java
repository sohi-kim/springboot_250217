package org.iclass.board.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.iclass.board.dto.CommunityDTO;
import org.iclass.board.mapper.CommunityMapper;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Slf4j
@Service
public class CommunityService {
	private CommunityMapper mapper;
	
	public List<CommunityDTO> getPageList(int currentPage){
		Map<String , Integer> map = new HashMap<>();
		map.put("startNo", 1); map.put("endNo", 10);		// 1페이지 글의 행번호
		
		return mapper.selectPageList(map);
	}

}
