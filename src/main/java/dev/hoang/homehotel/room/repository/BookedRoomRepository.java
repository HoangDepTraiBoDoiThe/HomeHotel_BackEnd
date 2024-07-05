package dev.hoang.homehotel.room.repository;

import dev.hoang.homehotel.room.model.BookedRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookedRoomRepository extends JpaRepository<BookedRoom, Long> {
}
