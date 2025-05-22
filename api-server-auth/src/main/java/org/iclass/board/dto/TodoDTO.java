package org.iclass.board.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iclass.board.model.Todo2Entity;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TodoDTO {
	private String id;
	private String title;
	private boolean done;
	private LocalDate createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate dueDate;

	public TodoDTO(Todo2Entity entity) {
		this.id = entity.getId();
		this.title = entity.getTitle();
		this.done = entity.isDone();
		this.createdAt = entity.getCreatedAt();
		this.dueDate = entity.getDueDate();
	}

	public static Todo2Entity toEntity(TodoDTO dto) {
		return Todo2Entity.builder()
						.id(dto.getId())
						.title(dto.getTitle())
						.done(dto.isDone())
				        .dueDate(dto.getDueDate())
						.build();
	}
}

