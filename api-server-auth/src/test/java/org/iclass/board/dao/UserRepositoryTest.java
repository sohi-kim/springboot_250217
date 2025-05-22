package org.iclass.board.dao;

import lombok.extern.slf4j.Slf4j;
import org.iclass.board.model.UsersEntity;
import org.iclass.board.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Slf4j
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void test(){
        assertNotNull(userRepository);
    }

    @Test
    @Disabled
    void createUser() {
        UsersEntity user =
                userRepository.save(new UsersEntity(null, "ksh@iclass.org", "12345", "admin", null));

        assertNotNull(user);
        log.info("User created: {}", user);
    }

    @Test
    @Disabled
    void getUser(){
        UsersEntity user = userRepository.findByUsernameAndPassword("John", "Doe");
        assertNotNull(user);
    }

    @Test
    @Disabled
    void getNoneUser(){
        UsersEntity user = userRepository.findByUsernameAndPassword("John", "Doee");
        assertNull(user);
    }
}