package org.iclass.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.iclass.board.model.UsersEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO implements UserDetails {

    public String token;
    private String username;
    private String password;
    private String id;          //user entity 에서 UUID 로 생성된 값.
    private Collection<? extends GrantedAuthority> authorities;
    private boolean social;
    public UserDTO(String username,
                          String password,
                          Collection<? extends GrantedAuthority> authorities
    ) {
//        super(username, password, authorities);
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.social = false;
    }
    // Collection<? extends GrantedAuthority>
    // : Collection 의 제너릭 타입 ? 은 GrantedAuthority 를 상속받은 타입이어야 한다.
    // : 권한(role) 은 여러가지 값을 가질 수 있도록 Collection (리스트, 맵, set) 타입.

    public static UserDTO of(UsersEntity entity){
        return UserDTO.builder().id(entity.getId()).username(entity.getUsername()).build();
    }

    @Getter @NoArgsConstructor
    public static class RequestDTO {
        private String username;
        private String password;
    }

    @Getter @AllArgsConstructor
    public static class ResponseDTO {
        private String username;
        private String id;
    }
}
