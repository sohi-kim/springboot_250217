package org.iclass.board.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BookableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String groupName;
    private String title;
    private String notes;
    private String sessionNumbers;
    private String availableDays;
    //오라클이 days 등 컬럼명 예약어 취급한 것을 변경
}