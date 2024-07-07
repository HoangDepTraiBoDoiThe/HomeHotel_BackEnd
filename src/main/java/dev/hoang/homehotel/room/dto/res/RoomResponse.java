package dev.hoang.homehotel.room.dto.res;

import dev.hoang.homehotel.room.model.Room;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {
    long id;
    int roomNumber;
    private String roomType;
    BigDecimal roomPrice;
    boolean isBooked;
    String roomPic;

    public RoomResponse(Room room) {
        this.id = room.getId();
        this.roomNumber = room.getRoomNumber();
        this.roomPrice = room.getRoomPrice();
        this.isBooked = room.isBooked();
        this.roomType = room.getRoomType();
        this.roomPic = convertBlobToString(room.getRoomPic());
    }

    String convertBlobToString(Blob roomPic) {
        if (roomPic == null) {
            return null;
        }
        try {
            byte[] roomPicBytes = roomPic.getBytes(1, (int) roomPic.length());
            return Base64.getEncoder().encodeToString(roomPicBytes);
        } catch (SQLException e) {
            throw new RuntimeException("Can't convert blob to string: " + e);
        }
    }
}
