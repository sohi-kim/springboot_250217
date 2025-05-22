package org.iclass.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.BookableDTO;
import org.iclass.board.service.BookablesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("bookables")
public class BookablesController {

    private final BookablesService bookablesService;

    @GetMapping
    public ResponseEntity<?> bookableList() {
        List<BookableDTO> list = bookablesService.list();
//        ResponseDTO<BookableDTO> response = ResponseDTO.<BookableDTO>builder().data(list).build();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBookable(@PathVariable int id) {
        BookableDTO dto = bookablesService.getBookable(id);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<?> createBookable(@RequestBody BookableDTO dto) {
        BookableDTO newdto = bookablesService.create(dto);
        if (newdto == null) {
            ResponseEntity.ok("creating bookable is fail.");
        }
        return ResponseEntity.ok(newdto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editBookable(@PathVariable int id,@RequestBody BookableDTO dto){
        BookableDTO modifyDto = bookablesService.modify(id,dto);
        if (modifyDto == null) {
           return ResponseEntity.ok().body("modifing bookable is fail.");
        }
        return ResponseEntity.ok(modifyDto);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteBookable(@PathVariable int id) {
        int result = bookablesService.delete(id);
        if(result==0){
            return ResponseEntity.ok().body("deleting bookable is fail.");
        }
        return ResponseEntity.ok().body(id);
    }
}
