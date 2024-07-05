package dev.hoang.homehotel.room.response;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Base64;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoomResponse {
    long id;
    int roomNumber;
    BigDecimal roomPrice;
    boolean isBooked;
    String roomPic;

    public RoomResponse(long id, int roomNumber, BigDecimal roomPrice, boolean isBooked, byte[] roomPicByte) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
        this.isBooked = isBooked;
        this.roomPic = roomPicByte != null ? Base64.getEncoder().encodeToString(roomPicByte) : null;
    }
}
