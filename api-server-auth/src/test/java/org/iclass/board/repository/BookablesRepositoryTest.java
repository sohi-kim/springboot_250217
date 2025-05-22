package org.iclass.board.repository;

import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.BookableDTO;
import org.iclass.board.model.BookableEntity;
import org.iclass.board.service.BookablesService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
class BookablesRepositoryTest {

    @Autowired
    private BookablesRepository bookablesRepository;

    @Autowired
    private BookablesService bookablesService;

    @Test
    void getBookables() {
        List<BookableEntity> list = bookablesRepository.findAll();
        log.info(list.toString());

    }

    @Test
    void bookableService(){
        List<BookableDTO> list = bookablesService.list();
        log.info(list.toString());
    }
}