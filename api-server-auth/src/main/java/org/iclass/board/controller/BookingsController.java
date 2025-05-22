package org.iclass.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.iclass.board.dto.BookingDTO;
import org.iclass.board.dto.RequestBookingDTO;
import org.iclass.board.service.BookingsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@RestController
@Slf4j
@SecurityRequirement(name = "bearer-key")
@RequestMapping("bookings")
public class BookingsController {

    private final BookingsService bookingsService;

    @GetMapping
    public ResponseEntity<?> bookingList(
//            @RequestBody RequestBookingDTO dto   //날짜 타입과 json 문자열 직렬,역직렬 문제
            @RequestParam(required = false) int bookableId,
            @RequestParam(required = false) String date_gte,@RequestParam(required = false) String date_lte
    ) {
        RequestBookingDTO dto = RequestBookingDTO.builder()
                .bookableId(bookableId)
                .date_gte(LocalDate.parse(date_gte))
                .date_lte(LocalDate.parse(date_lte))
                        .build();
        List<BookingDTO> list = bookingsService.weekBookings(dto);
//        ResponseDTO<BookingDTO> response = ResponseDTO.<BookingDTO>builder().data(list).build();

        log.info("Bookings  : 🎡{} ", list);
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBooking(@PathVariable int id) {
        BookingDTO dto = bookingsService.getBooking(id);

        return ResponseEntity.ok().body(dto);
    }

    @PostMapping
    public ResponseEntity<?> createBooking(@RequestBody BookingDTO dto) {
        BookingDTO newDto = bookingsService.create(dto);
        if (newDto == null) {
            ResponseEntity.ok("creating booking is fail.");
        }
        return ResponseEntity.ok(newDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> editBooking(@PathVariable int id, @RequestBody BookingDTO dto) {
        BookingDTO modifyDto = bookingsService.modify(id, dto);
        if (modifyDto == null) {
            return ResponseEntity.ok().body("modifying booking is fail.");
        }
        return ResponseEntity.ok(modifyDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBooking(@PathVariable int id) {
        int result = bookingsService.delete(id);
        if (result == 0) {
            return ResponseEntity.ok().body("deleting booking is fail.");
        }
        return ResponseEntity.ok().body(id);
    }
}
