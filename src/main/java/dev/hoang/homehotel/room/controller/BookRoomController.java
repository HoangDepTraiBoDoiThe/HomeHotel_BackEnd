package dev.hoang.homehotel.room.controller;

import dev.hoang.homehotel.room.dto.req.BookRoomRequest;
import dev.hoang.homehotel.room.dto.res.BookedRoomResponse;
import dev.hoang.homehotel.room.model.BookedRoom;
import dev.hoang.homehotel.room.service.BookedRoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/rooms/{id}/booking")
@RequiredArgsConstructor
public class BookRoomController {
    private final BookedRoomService bookedRoomService;

    @PostMapping
    BookedRoomResponse bookARoom(@RequestBody BookRoomRequest bookRoomRequest, @PathVariable long id) {
        BookedRoom bookedRoom = bookedRoomService.createBookedRoom(bookRoomRequest);
        return new BookedRoomResponse(bookedRoom);
    }

}
