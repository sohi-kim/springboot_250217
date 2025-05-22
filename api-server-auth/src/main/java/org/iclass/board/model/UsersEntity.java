package org.iclass.board.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = "username")})
public class UsersEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String username;    //이메일 또는 일반 문자열 모두 가능

    private String password;    //OAuth 에서 SSO 구현시 패스워드 필요없음
    @Builder.Default
    private String role="USER";
    private String authProvider;   //oauth 에서 사용할 공급자
}
