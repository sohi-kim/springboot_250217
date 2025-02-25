package org.iclass.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.iclass.board.dto.CommunityDTO;

@Mapper
public interface CommunityMapper {
	  List<CommunityDTO> selectPageList(Map<String , Integer> map);
	  int insert(CommunityDTO dto);
}
