package org.iclass.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FileRequestDTO {

    //업로드파일을 저장하기 위한 객체
    private MultipartFile file;
    //한번에 여러개의 파일을 업로드
    private List<MultipartFile> fileS;

}
