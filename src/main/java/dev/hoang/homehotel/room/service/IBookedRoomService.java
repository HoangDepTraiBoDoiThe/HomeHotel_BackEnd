package dev.hoang.homehotel.room.service;

import dev.hoang.homehotel.room.dto.req.BookRoomRequest;
import dev.hoang.homehotel.room.model.BookedRoom;

import java.util.List;

public interface IBookedRoomService {
    BookedRoom getBookedRoomById(long id);
    BookedRoom getBookedRoomByConfirmationCode(String code);
    List<BookedRoom> getAllBookedHistoryOfRoomById(long id);
    void deleteBookedRoomById(long id);
    BookedRoom createBookedRoom(BookRoomRequest bookRoomRequest);
    BookedRoom updateBookedRoom(BookRoomRequest bookRoomRequest);
}
