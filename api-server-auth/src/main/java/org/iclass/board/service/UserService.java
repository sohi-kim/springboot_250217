package org.iclass.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.UserDTO;
import org.iclass.board.model.UsersEntity;
import org.iclass.board.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
public class UserService {

    private final UserRepository userRepository;

    // 새로운 사용자 등록
    //이 메소드에서 UserEntity 파라미터 객체의 변수를 수정할 수 없음
    public UsersEntity createUser(final UsersEntity user) {
        if(user == null || user.getUsername() ==null) {
            throw new RuntimeException("필수 인자값이 없습니다.");
        }
        final String username = user.getUsername();

        if(userRepository.existsByUsername(username)) {
            log.warn("이미 존재하는 username : {}",username);
            throw new RuntimeException("Username already exist.");
        }

        return userRepository.save(user);
    }

//    public UserEntity getByCredentials(final String username, final String password) {
//        return userRepository.findByUsernameAndPassword(username, password);
//    }

    public UsersEntity getByCredentials(final String username,
                                        final String password, final PasswordEncoder encoder) {

        final UsersEntity user = userRepository.findByUsername(username);
        if(user !=null && encoder.matches(password, user.getPassword())) {
            return user;
        }
        return null;
    }  //BCryptPasswordEncoder 는 같은 값을 인코딩하더라도 할때마다 값이 다르다.
    //  salt 를 붙여 인코딩(salting) 하기 때문.


    public boolean isExist(String username) {
        return userRepository.existsByUsername(username);
    }

    public UserDTO get(String userid) {
        Optional<UsersEntity> optionalUser = userRepository.findById(userid);
        return optionalUser.map(UserDTO::of).orElse(null);
    }
}
