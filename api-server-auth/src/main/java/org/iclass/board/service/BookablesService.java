package org.iclass.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.BookableDTO;
import org.iclass.board.model.BookableEntity;
import org.iclass.board.repository.BookablesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookablesService {
    private final BookablesRepository bookablesRepository;

    public List<BookableDTO> list() {
        List<BookableEntity> list = bookablesRepository.findAll();
        log.info("Found {} bookables", list.size());
        return list.stream().map(BookableDTO::new).toList();
    }


    public BookableDTO getBookable(int id) {
        Optional<BookableEntity> optional = bookablesRepository.findById(id);
        return optional.map(BookableDTO::new).orElse(null);
    }

    public BookableDTO create(BookableDTO dto) {
        BookableEntity bookableEntity = dto.toEntity();
        bookablesRepository.save(bookableEntity);
        return new BookableDTO(bookableEntity);
    }

    public BookableDTO modify(int id, BookableDTO dto) {
        Optional<BookableEntity> optional = bookablesRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        BookableEntity bookableEntity = dto.toEntity();
        bookablesRepository.save(bookableEntity);
        return new BookableDTO(bookableEntity);
    }

    public int delete(int id) {
        Optional<BookableEntity> optional = bookablesRepository.findById(id);
        if (optional.isEmpty()) {
            return 0;
        }
        bookablesRepository.deleteById(id);
        return id;
    }
}
