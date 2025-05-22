package org.iclass.board.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class BookingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int bookableId;

    @Column(nullable = false)
    private int bookerId;

    private String title;
    private String sessionName;
    private LocalDate bookingDate; //오라클이 date 컬럼명은 예약어 취급.
    private String notes;
}