package org.iclass.board.repository;

import org.iclass.board.model.BookableEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookablesRepository extends JpaRepository<BookableEntity,Integer> {
}
