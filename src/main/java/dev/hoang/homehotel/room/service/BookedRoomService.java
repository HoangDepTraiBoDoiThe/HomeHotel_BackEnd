package dev.hoang.homehotel.room.service;

import dev.hoang.homehotel.room.dto.req.BookRoomRequest;
import dev.hoang.homehotel.room.exception.BookedRoomException;
import dev.hoang.homehotel.room.exception.RoomException;
import dev.hoang.homehotel.room.model.BookedRoom;
import dev.hoang.homehotel.room.model.Room;
import dev.hoang.homehotel.room.repository.BookedRoomRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static dev.hoang.homehotel.global.utils.index.generateNumericCode;

@Service
@RequiredArgsConstructor
public class BookedRoomService implements IBookedRoomService{
    private final BookedRoomRepository bookedRoomRepository;
    private final RoomService roomService;
    private static final Logger logger = LoggerFactory.getLogger(BookedRoomService.class);

    @Override
    @Transactional(readOnly = true)
    public BookedRoom getBookedRoomById(long id) {
        logger.info("Fetching booked room with id: {}", id);
        return bookedRoomRepository.findById(id)
                .orElseThrow(() -> new BookedRoomException("Booked room not found with id: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public BookedRoom getBookedRoomByConfirmationCode(String code) {
        logger.info("Fetching booked room with confirmation code: {}", code);
        return bookedRoomRepository.findBookedRoomByConfirmationCode(code)
                .orElseThrow(() -> new BookedRoomException("Booked room not found with confirmation code: " + code));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookedRoom> getAllBookedHistoryOfRoomById(long id) {
        logger.info("Fetching booking history for room with id: {}", id);
        return bookedRoomRepository.findAllByRoomId(id);
    }

    @Override
    @Transactional
    public void deleteBookedRoomById(long id) {
        logger.info("Deleting booked room with id: {}", id);
        bookedRoomRepository.deleteById(id);
    }

    @Override
    @Transactional
    public BookedRoom createBookedRoom(BookRoomRequest bookRoomRequest) {
        logger.info("Creating booked room for room id: {}", bookRoomRequest.getRoomId());
        Room room = roomService.getRoomById(bookRoomRequest.getRoomId());
        if (!room.isBooked()) {
            BookedRoom bookedRoom = new BookedRoom(bookRoomRequest, room, generateNumericCode(7));
            return bookedRoomRepository.save(bookedRoom);
        } else {
            throw new RoomException("Room with id " + bookRoomRequest.getRoomId() + " is not available.");
        }
    }

    @Override
    @Transactional
    public BookedRoom updateBookedRoom(BookRoomRequest bookRoomRequest) {
        logger.info("Updating booked room with id: {}", bookRoomRequest.getRoomId());
        BookedRoom existingBookedRoom = getBookedRoomById(bookRoomRequest.getRoomId());
        // Update the fields of existingBookedRoom with the values from bookRoomRequest
        // ... (implement the update logic here)
        return bookedRoomRepository.save(existingBookedRoom);
    }
}
