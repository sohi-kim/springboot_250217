package org.iclass.board.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
// 이 클래스는 db와 직접 연결되는 엔티티
//   -변수들을 테이블 컬럼으로 만듭니다.
//   -클래스 이름으로 테이블을 생성합니다.
@Table(name = "tbl_user")
//   - 테이블이름변경, 제약조건 등의 설정을 합니다.
public class UserEntity {

    @Id      // PK 설정
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;            //오라클 기존 시퀀스 대신에 GenerationType.IDENTITY

    @Column(nullable = false,unique = true,updatable = false)
    private String username;   //로그인 계정(이메일, userid 등). 제약 조건 unique
    @Column(nullable = false)
    private String password;
    private String role;

    private String nickname;
    private String picture;    //프로필이미지 파일명

    @CreatedDate @Column(updatable = false)
    private LocalDate joinDate;
}
