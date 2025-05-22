package org.iclass.board.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.BookingDTO;
import org.iclass.board.dto.RequestBookingDTO;
import org.iclass.board.model.BookingEntity;
import org.iclass.board.repository.BookingsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class BookingsService {
    private final BookingsRepository bookingsRepository;

    public List<BookingDTO> weekBookings(RequestBookingDTO dto) {
        List<BookingEntity> list = bookingsRepository
                .findByBookingDateBetweenAndBookableId(dto.getDate_gte(),dto.getDate_lte(),dto.getBookableId());

        if(list.isEmpty()) {
            return new ArrayList<>();   //null 이면 응답처리 못함.
        }

        return list.stream().map(BookingDTO::new).toList();
    }

    public BookingDTO getBooking(int id) {
        Optional<BookingEntity> optional = bookingsRepository.findById(id);
        return optional.map(BookingDTO::new).orElse(null);
    }

    public BookingDTO create(BookingDTO dto) {
        BookingEntity bookingEntity = dto.toEntity();
        bookingsRepository.save(bookingEntity);
        return new BookingDTO(bookingEntity);
    }

    public BookingDTO modify(int id, BookingDTO dto) {
        Optional<BookingEntity> optional = bookingsRepository.findById(id);
        if (optional.isEmpty()) {
            return null;
        }
        BookingEntity bookingEntity = dto.toEntity();
        bookingsRepository.save(bookingEntity);
        return new BookingDTO(bookingEntity);
    }

    public int delete(int id) {
        Optional<BookingEntity> optional = bookingsRepository.findById(id);
        if (optional.isEmpty()) {
            return 0;
        }
        bookingsRepository.deleteById(id);
        return id;
    }



}
