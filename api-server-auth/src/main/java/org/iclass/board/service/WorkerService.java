package org.iclass.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.WorkerDTO;
import org.iclass.board.model.WorkerEntity;
import org.iclass.board.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
@Service
public class WorkerService {
    private final WorkerRepository workerRepository;

    public List<WorkerDTO> getAllWorkers() {
        List<WorkerEntity> list = workerRepository.findAll();
        return list.stream().map(WorkerDTO::new).toList();
    }

    public WorkerDTO getWorker(int id) {
        Optional<WorkerEntity> optional = workerRepository.findById(id);
        return optional.map(WorkerDTO::new).orElse(null);
    }

    public WorkerDTO modify(int id, WorkerDTO worker) {
        Optional<WorkerEntity> optional = workerRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }else {
            WorkerEntity entity = optional.get();
            if(entity.getImg()!=null && worker.getImg() !=null && !entity.getImg().equals(worker.getImg())) {
                deleteFile(entity.getImg());
            }

        }
        WorkerEntity entity = worker.toEntity();
        workerRepository.save(entity);
        return new WorkerDTO(entity);
    }


    public void deleteFile(String filename) {
        // File file = new File("c://upload/"+filename);
        File file = new File("/app/upload/"+filename);

        if (file.delete()) {
            log.info("File deleted successfully.");
        } else {
            log.info("Failed to delete the file.");
        }
    }

}
