package org.iclass.mvcEx.dto;

import org.springframework.web.multipart.MultipartFile;

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
public class Gallery {
	private String title;
	private String writer;
	// 1개의 업로드 파일을 저장할 변수
	private MultipartFile file;

	// gallery.html 의 input 요소 외에 db에 파일명을 저장하기 위한 변수
	private String fileNames;
}




