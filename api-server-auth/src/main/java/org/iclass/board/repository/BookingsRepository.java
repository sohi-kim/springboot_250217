package org.iclass.board.repository;

import org.iclass.board.model.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookingsRepository extends JpaRepository<BookingEntity,Integer> {
    List<BookingEntity> findByBookingDateBetweenAndBookableId(LocalDate bookingDate, LocalDate bookingDate2, int bookableId );
}
