package dev.hoang.homehotel.room.service;

import dev.hoang.homehotel.room.dto.req.BookRoomRequest;
import dev.hoang.homehotel.room.exception.BookedRoomException;
import dev.hoang.homehotel.room.model.BookedRoom;
import dev.hoang.homehotel.room.model.Room;
import dev.hoang.homehotel.room.repository.BookedRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static dev.hoang.homehotel.utils.index.generateNumericCode;

@Service
@RequiredArgsConstructor
public class BookedRoomService implements IBookedRoomService{
    private final BookedRoomRepository bookedRoomRepository;
    private final RoomService roomService;

    @Override
    public BookedRoom getBookedRoomBy(long id) {
        return bookedRoomRepository.findById(id).orElseThrow(() ->  new BookedRoomException("Booked room not found"));
    }

    @Override
    public List<BookedRoom> getAllBookedHistoryOfRoomById(long id) {
        return bookedRoomRepository.findAllByRoomId(id);
    }

    @Override
    public void deleteBookedRoomById(long id) {
        bookedRoomRepository.deleteById(id);
    }

    @Override
    public BookedRoom createBookedRoom(BookRoomRequest bookRoomRequest) {
        Room room = roomService.getRoomById(bookRoomRequest.getRoomId());
        if (!room.isBooked()) {
            BookedRoom bookedRoom = new BookedRoom(bookRoomRequest, room, generateNumericCode(7));
            return bookedRoomRepository.save(bookedRoom);
        }
        else {
            throw new BookedRoomException("This room is no longer available.");
        }
    }

    @Override
    public BookedRoom updateBookedRoom(BookRoomRequest bookRoomRequest) {
        return null;
    }
}
