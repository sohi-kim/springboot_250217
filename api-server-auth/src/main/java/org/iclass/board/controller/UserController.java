package org.iclass.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.ResponseDTO;
import org.iclass.board.dto.UserDTO;
import org.iclass.board.model.UsersEntity;
import org.iclass.board.security.TokenProvider;
import org.iclass.board.service.UserService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserController {

    private final UserService userService;
    private final TokenProvider tokenProvider;

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    @GetMapping("/auth")
    public ResponseEntity<?> auth(@AuthenticationPrincipal String userid) {
        log.info("Authenticating user {}", userid);     // user 식별값
           // dto username 은 계정명
        if(userid !=null && !userid.equals("anonymousUser"))
            return ResponseEntity.ok(true);
        else
            return ResponseEntity.status(HttpStatusCode.valueOf(403)).build();
    }   // 권한이 없는 접근은 403 코드 응답

    @GetMapping("/checkout/{username}")
    public ResponseEntity<?> is(@PathVariable String username) {
        boolean response = userService.isExist(username);

        return ResponseEntity.ok().body(!response);

    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody UserDTO.RequestDTO userDTO) {
        try{
            log.info("user DTO : {}", userDTO.getUsername(), userDTO.getPassword());
            if(userDTO == null || (userDTO.getPassword() == null || userDTO.getUsername()==null)){
                throw new RuntimeException("Invalid Password.");
            }
            UsersEntity user = UsersEntity.builder()
                    .username(userDTO.getUsername())
                    .password(passwordEncoder.encode(userDTO.getPassword() ))
                    .build();

            UsersEntity createdUser = userService.createUser(user);
            UserDTO.ResponseDTO createdUserDTO = new UserDTO.ResponseDTO(createdUser.getUsername(),createdUser.getId());
            return ResponseEntity.ok(createdUserDTO);

        }catch (Exception e){
            ResponseDTO<Object> responseDTO = ResponseDTO.builder()
                    .error(e.getMessage())
                    .build();
            return ResponseEntity.badRequest().body(responseDTO);
        }
    }


    @PostMapping("/signin")
    public ResponseEntity<?> login(@RequestBody UserDTO.RequestDTO userDTO) {
        UsersEntity entity = userService.getByCredentials(userDTO.getUsername(), userDTO.getPassword(),passwordEncoder);
        if(entity !=null){   //아이디,패스워드 확인 성공하면

            // 로그인 토큰을 만든다.
            final String token = tokenProvider.generateToken(entity);
            log.info("token : {}", token);
            // 토큰값을 dto 에 저장한다. // dto 는 스프링 시큐리티 UserDetails 를 implements 한다.
            final UserDTO responseUserDTO = UserDTO.builder()
                    .username(entity.getUsername())
//                    .id(entity.getId())
                    .authorities(Collections.singleton(new SimpleGrantedAuthority(entity.getRole())))
                    .token(token)  //추가
                    .build();
            // 응답으로 dto를 보내준다.
            return ResponseEntity.ok(responseUserDTO);
        }else{
            ResponseDTO<Object> responseDTO = ResponseDTO.builder()
                    .error("Login Failed")
                    .build();

            return ResponseEntity.ok().body(responseDTO); //.build();
        }
    }

}
