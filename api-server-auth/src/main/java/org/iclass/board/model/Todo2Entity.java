package org.iclass.board.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table
public class Todo2Entity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;
	private String userId;
	private String title;
	private boolean done;

	@CreatedDate
	private LocalDate createdAt;
	private LocalDate dueDate;
}
