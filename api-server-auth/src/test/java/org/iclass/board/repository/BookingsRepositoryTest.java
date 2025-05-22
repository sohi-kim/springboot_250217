package org.iclass.board.repository;

import lombok.extern.slf4j.Slf4j;
import org.iclass.board.model.BookingEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;
@SpringBootTest
@Slf4j
class BookingsRepositoryTest {
    @Autowired
    private BookingsRepository bookingsRepository;

    @Test
    void weekBookings(){

        List<BookingEntity> list = bookingsRepository
                .findByBookingDateBetweenAndBookableId(LocalDate.parse("2024-09-22"),
                        LocalDate.parse("2024-09-28"),1);
        log.info(list.toString());
    }
}