package dev.hoang.homehotel.room.controller;

import dev.hoang.homehotel.room.assembler.BookedRoomAssembler;
import dev.hoang.homehotel.room.dto.req.BookRoomRequest;
import dev.hoang.homehotel.room.dto.res.BookedRoomResponse;
import dev.hoang.homehotel.room.model.BookedRoom;
import dev.hoang.homehotel.room.service.BookedRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rooms/{id}/booking")
@RequiredArgsConstructor
public class BookRoomController {
    private final BookedRoomService bookedRoomService;
    private final BookedRoomAssembler bookedRoomAssembler;

    @PostMapping
    public ResponseEntity<EntityModel<BookedRoomResponse>> bookARoom(@RequestBody BookRoomRequest bookRoomRequest, @PathVariable long id) {
        BookedRoom bookedRoom = bookedRoomService.createBookedRoom(bookRoomRequest);
        BookedRoomResponse bookedRoomResponse = new BookedRoomResponse(bookedRoom);
        return ResponseEntity.ok(bookedRoomAssembler.toModel(bookedRoomResponse));
    }

    @GetMapping
    public ResponseEntity<EntityModel<BookedRoomResponse>> getBookedRoomByConfirmationCode(@RequestParam String bookedRoomConfirmationCode) {
        BookedRoom bookedRoom = bookedRoomService.getBookedRoomByConfirmationCode(bookedRoomConfirmationCode);
        BookedRoomResponse bookedRoomResponse = new BookedRoomResponse(bookedRoom);
        return ResponseEntity.ok(bookedRoomAssembler.toModel(bookedRoomResponse));
    }
}
