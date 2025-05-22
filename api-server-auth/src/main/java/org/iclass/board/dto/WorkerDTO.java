package org.iclass.board.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iclass.board.model.WorkerEntity;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class WorkerDTO {

    private Integer id;
    private String name;
    private String img;
    private String title;
    private String notes;

    public WorkerDTO(WorkerEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.img = entity.getImg();
        this.title = entity.getTitle();
        this.notes = entity.getNotes();
    }

    public WorkerEntity toEntity() {
        return  WorkerEntity.builder()
                .id(this.id)
                .name(this.name)
                .img(this.img)
                .title(this.title)
                .notes(this.notes)
                .build();
    }
}
