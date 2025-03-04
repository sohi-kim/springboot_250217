package org.iclass.board.mapper;

import java.util.List;

import org.iclass.board.dto.CommunityCommentDTO;

public interface CommunityCommentsMapper {
	int insert(CommunityCommentDTO dto);
	int delete(int idx);
	List<CommunityCommentDTO> selectCommentList(int mref); 
	int selectCommentCount(int mref);
	int updateCommentCount (int mref);
}
