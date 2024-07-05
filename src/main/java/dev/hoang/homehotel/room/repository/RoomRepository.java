package dev.hoang.homehotel.room.repository;

import dev.hoang.homehotel.room.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
