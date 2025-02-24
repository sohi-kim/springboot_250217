package org.iclass.mvcEx.service;

import java.io.File;
import java.io.IOException;

import org.iclass.mvcEx.dto.Gallery;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class GalleryUploadService {
	//상수
	private final static String UPLOAD_PATH = "c:\\upload";
	
	// db mapper 의존관계 필요함.
	
	// 1개의 파일을 업로드 하는 메소드
	public void uploadOne(Gallery dto) {
		// 서버가 dto 중에 업로드파일(file 변수)을 지정된 폴더에 저장
		MultipartFile file = dto.getFile();
		try {
			if(file.getSize() != 0 ) {  //가져온 파일의 크기가 0이 아닐때만
				log.info("파일의 이름/크기/타입 : {},{},{}",
						file.getOriginalFilename(), 
						file.getSize(),
						file.getContentType());
				// 해당 파일을 서버의 로컬시스템의 File 객체로 만들기
				File uploadFile = new File(UPLOAD_PATH + "\\"
								+ file.getOriginalFilename());
				// 위의 File 객체를 실제로 저장하기
				file.transferTo(uploadFile);
			}
		}catch (IOException e) {
			log.debug("파일 업로드 예외 : {}", e.getMessage());
		}
	}
}





