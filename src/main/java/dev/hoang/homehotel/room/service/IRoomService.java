package dev.hoang.homehotel.room.service;

import dev.hoang.homehotel.room.dto.req.BookRoomRequest;
import dev.hoang.homehotel.room.dto.res.BookedRoomResponse;
import dev.hoang.homehotel.room.dto.req.RoomRequest;
import dev.hoang.homehotel.room.dto.res.RoomResponse;
import dev.hoang.homehotel.room.model.Room;

import java.util.List;

public interface IRoomService {
    Room getRoomById(long id);
    List<Room> getAllRooms();
    Room createRoom(RoomRequest roomRequest);
    Room updateRoom(long id, RoomRequest roomRequest);
    void deleteRoom(long id);
}
