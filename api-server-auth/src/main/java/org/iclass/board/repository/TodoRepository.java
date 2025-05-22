package org.iclass.board.repository;

import org.iclass.board.model.Todo2Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo2Entity, String>{
	List<Todo2Entity> findByUserId(String userId);
}
