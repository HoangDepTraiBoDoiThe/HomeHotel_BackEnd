package dev.hoang.homehotel.room.repository;

import dev.hoang.homehotel.room.model.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long> {
    public List<BookedRoom> findAllByRoomId(long id);
}
