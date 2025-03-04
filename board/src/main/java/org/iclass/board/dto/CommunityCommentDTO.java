package org.iclass.board.dto;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CommunityCommentDTO {
	private int idx;
	private int mref;
	private String writer;
	private String content;
	private Date createdAt;
	private String ip;
	
	
	
}
