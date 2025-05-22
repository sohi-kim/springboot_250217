package org.iclass.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.WorkerDTO;
import org.iclass.board.service.WorkerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@SecurityRequirement(name = "bearer-key")
@RequestMapping("/users")
public class WorkerController {

    private final WorkerService workerService;

    @GetMapping
    public ResponseEntity<?> getAllWorkers() {
        List<WorkerDTO> list = workerService.getAllWorkers();
//        ResponseDTO<WorkerDTO> responseDTO = ResponseDTO.<WorkerDTO>builder().data(list).build();

        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getWorker(@PathVariable int id) {
        WorkerDTO dto = workerService.getWorker(id);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editWorker(@PathVariable int id, @RequestBody WorkerDTO worker) {
        WorkerDTO dto = workerService.modify(id,worker);
        if (dto == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().body(dto);
    }

}
