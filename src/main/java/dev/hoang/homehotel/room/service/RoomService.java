package dev.hoang.homehotel.room.service;

import dev.hoang.homehotel.room.dto.req.BookRoomRequest;
import dev.hoang.homehotel.room.dto.req.RoomRequest;
import dev.hoang.homehotel.room.dto.res.BookedRoomResponse;
import dev.hoang.homehotel.room.dto.res.RoomResponse;
import dev.hoang.homehotel.room.model.Room;
import dev.hoang.homehotel.room.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoomService implements IRoomService{

    private final RoomRepository roomRepository;

    @Override
    public RoomResponse getRoomById(long id) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        return new RoomResponse(room);
    }
    @Override
    public List<RoomResponse> getAllRooms() {
        return roomRepository.findAll().stream().map(RoomResponse::new).collect(Collectors.toList());
    }

    @Override
    public RoomResponse createRoom(RoomRequest roomRequest) {
        Room newRoom = roomRepository.save(new Room(roomRequest));
        return new RoomResponse(newRoom);
    }

    @Override
    public RoomResponse updateRoom(long id, RoomRequest roomRequest) {
        Room room = roomRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        room.setRoomNumber(roomRequest.getRoomNumber());
        room.setRoomPrice(roomRequest.getRoomPrice());
        room.setBooked(roomRequest.isRoomBooked());
        room.setRoomPic(roomRequest.roomPicToBlob());
        Room updatedRoom = roomRepository.save(room);
        return new RoomResponse(updatedRoom);
    }

    @Override
    public void deleteRoom(long id) {
        roomRepository.deleteById(id);
    }

    @Override
    public BookedRoomResponse bookRoom(long roomId, BookRoomRequest bookRoomRequest) {
        return null;
    }

    @Override
    public List<BookedRoomResponse> getAllBookedRooms() {
        return null;
    }

    @Override
    public BookedRoomResponse getBookedRoomById(long id) {
        return null;
    }

    @Override
    public void deleteBookedRoom(long id) {

    }
}
