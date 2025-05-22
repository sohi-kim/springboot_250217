package org.iclass.board.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@Table(name = "tbl_todo")
@Entity
public class TodoEntity {

    @Id   //기본키
    @Column(length = 36, updatable = false, nullable = false)
    // @GeneratedValue(strategy = GenerationType.UUID)  //오라클
    // MySQL 은 UUID() 함수로 만들 수 있습니다.
    private String todo_id;

    @PrePersist
    public void generateUUID() {
        this.todo_id = UUID.randomUUID().toString();
    }

    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String username;
    private boolean done;
    
    @CreatedDate @Column(updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    private LocalDateTime updatedAt;


}
