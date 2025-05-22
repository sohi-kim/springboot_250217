package org.iclass.board.repository;

import org.iclass.board.model.UsersEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UsersEntity,String> {
    UsersEntity findByUsername(String username);
    Boolean existsByUsername(String username);
    UsersEntity findByUsernameAndPassword(String username, String password);
}
