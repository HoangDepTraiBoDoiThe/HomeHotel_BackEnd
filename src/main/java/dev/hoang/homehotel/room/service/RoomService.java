package dev.hoang.homehotel.room.service;

import dev.hoang.homehotel.room.dto.req.BookRoomRequest;
import dev.hoang.homehotel.room.dto.req.RoomRequest;
import dev.hoang.homehotel.room.dto.res.BookedRoomResponse;
import dev.hoang.homehotel.room.dto.res.RoomResponse;
import dev.hoang.homehotel.room.exception.RoomException;
import dev.hoang.homehotel.room.model.Room;
import dev.hoang.homehotel.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService{

    private final RoomRepository roomRepository;

    @Override
    public Room getRoomById(long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RoomException("Room not found"));
    }

    @Override
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    @Override
    public Room createRoom(RoomRequest roomRequest) {
        return roomRepository.save(new Room(roomRequest));
    }

    @Override
    public Room updateRoom(long id, RoomRequest roomRequest) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RoomException("Room not found. Can not update this room"));
        room.setRoomNumber(roomRequest.getRoomNumber());
        room.setRoomPrice(roomRequest.getRoomPrice());
        room.setBooked(roomRequest.isRoomBooked());
        room.setRoomPic(roomRequest.roomPicToBlob());
        return roomRepository.save(room);
    }
    @Override
    public void deleteRoom(long id) {
        roomRepository.deleteById(id);
    }

}
