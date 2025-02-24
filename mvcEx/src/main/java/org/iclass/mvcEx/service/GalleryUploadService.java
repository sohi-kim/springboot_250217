package org.iclass.mvcEx.service;

import org.iclass.mvcEx.dto.Gallery;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GalleryUploadService {
	//상수
	private final static String UPLOAD_PATH = "c:\\upload";
	
	// db mapper 의존관계 필요함.
	
	// 1개의 파일을 업로드 하는 메소드
	public void uploadOne(Gallery dto) {
		
	}
}
