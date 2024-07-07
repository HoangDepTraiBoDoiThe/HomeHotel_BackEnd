package dev.hoang.homehotel.room.model;

import dev.hoang.homehotel.room.dto.req.RoomRequest;
import dev.hoang.homehotel.room.dto.res.RoomResponse;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Blob;
import java.util.Base64;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue
    @Setter(AccessLevel.PRIVATE) private long id;
    private int roomNumber;
    private BigDecimal roomPrice;
    private String roomType;
    private boolean isBooked;
    @Lob
    private Blob roomPic;

    public Room(RoomRequest roomRequest) {
        this.roomNumber = roomRequest.getRoomNumber();
        this.roomPrice = roomRequest.getRoomPrice();
        this.roomType = roomRequest.getRoomType();
        this.isBooked = false;
        this.roomPic = roomRequest.roomPicToBlob();
    }
}
