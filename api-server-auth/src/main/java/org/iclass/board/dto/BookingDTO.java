package org.iclass.board.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.iclass.board.model.BookingEntity;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class BookingDTO {

    private int id;
    private int bookerId;
    private int bookableId;
    private String title;
    private String session;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date; //오라클이 date 컬럼명은 예약어 취급.
    private String notes;


    public BookingDTO(BookingEntity entity){
        this.id = entity.getId();
        this.bookerId = entity.getBookerId();
        this.bookableId = entity.getBookableId();
        this.title = entity.getTitle();
        this.session= entity.getSessionName();
        this.date=entity.getBookingDate();
        this.notes=entity.getNotes();
    }

    public BookingEntity toEntity(){
        return BookingEntity.builder()
                .id(this.id)
                .bookerId(this.bookerId)
                .bookableId(this.bookableId)
                .title(this.title)
                .sessionName(this.session)
                .bookingDate(this.date)
                .notes(this.notes)
                .build();
    }
}
