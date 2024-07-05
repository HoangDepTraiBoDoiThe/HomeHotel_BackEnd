package dev.hoang.homehotel.room.service;

import dev.hoang.homehotel.room.dto.req.BookRoomRequest;
import dev.hoang.homehotel.room.dto.res.BookedRoomResponse;
import dev.hoang.homehotel.room.dto.res.RoomResponse;

import java.util.List;

public interface IBookedRoomService {
    BookedRoomResponse getBookedRoomBy(long id);
    List<BookedRoomResponse> getAllBookedRooms();
    void deleteBookedRoomById(long id);
    BookedRoomResponse createBookedRoom(BookRoomRequest bookRoomRequest);
    BookedRoomResponse updateBookedRoom(BookRoomRequest bookRoomRequest);
}
