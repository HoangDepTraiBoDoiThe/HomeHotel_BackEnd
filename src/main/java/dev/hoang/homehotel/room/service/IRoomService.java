package dev.hoang.homehotel.room.service;

import dev.hoang.homehotel.room.dto.req.BookRoomRequest;
import dev.hoang.homehotel.room.dto.res.BookedRoomResponse;
import dev.hoang.homehotel.room.dto.req.RoomRequest;
import dev.hoang.homehotel.room.dto.res.RoomResponse;

import java.util.List;

public interface IRoomService {
    RoomResponse getRoomById(long id);
    List<RoomResponse> getAllRooms();
    RoomResponse createRoom(RoomRequest roomRequest);
    RoomResponse updateRoom(long id, RoomRequest roomRequest);
    void deleteRoom(long id);
    BookedRoomResponse bookRoom(long roomId, BookRoomRequest bookRoomRequest);
    List<BookedRoomResponse> getAllBookedRooms();
    BookedRoomResponse getBookedRoomById(long id);
    void deleteBookedRoom(long id);
}
