package dev.hoang.homehotel.room.service;

import dev.hoang.homehotel.room.dto.req.BookRoomRequest;
import dev.hoang.homehotel.room.dto.res.BookedRoomResponse;
import dev.hoang.homehotel.room.exception.BookedRoomException;
import dev.hoang.homehotel.room.model.BookedRoom;
import dev.hoang.homehotel.room.model.Room;
import dev.hoang.homehotel.room.repository.BookedRoomRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public class BookedRoomService implements IBookedRoomService{
    private final BookedRoomRepository bookedRoomRepository;
    private final RoomService roomService;

    @Override
    public BookedRoomResponse getBookedRoomBy(long id) {
        BookedRoom bookedRoom = bookedRoomRepository.findById(id).orElseThrow(() ->  new BookedRoomException("Booked room not found"));
        return new BookedRoomResponse(bookedRoom);
    }

    @Override
    public List<BookedRoomResponse> getAllBookedRooms() {
        return bookedRoomRepository.findAll().stream().map(BookedRoomResponse::new).collect(Collectors.toList());
    }

    @Override
    public void deleteBookedRoomById(long id) {
        bookedRoomRepository.deleteById(id);
    }

    @Override
    public BookedRoomResponse createBookedRoom(BookRoomRequest bookRoomRequest) {
        return null;
    }

    @Override
    public BookedRoomResponse updateBookedRoom(BookRoomRequest bookRoomRequest) {
        return null;
    }
}
