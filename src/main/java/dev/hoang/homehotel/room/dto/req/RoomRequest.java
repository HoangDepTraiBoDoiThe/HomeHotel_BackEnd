package dev.hoang.homehotel.room.dto.req;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;

@Data

public class RoomRequest {
    private int roomNumber;             // Non-null, unique
    private boolean isRoomBooked;        // Non-null, positive
    private String roomType;
    private BigDecimal roomPrice;        // Non-null, positive
    @Lob
    private MultipartFile roomPic;       // Optional (for uploading a new picture)

    public RoomRequest(BigDecimal roomPrice, MultipartFile roomPic, String roomType, int roomNumber) {
        this.roomPrice = roomPrice;
        this.roomPic = roomPic;
        this.roomType = roomType;
        this.roomNumber = roomNumber;
    }

    public Blob roomPicToBlob() {
        if (roomPic != null && !roomPic.isEmpty()) {
            try {
                byte[] multiPartFileBytes = roomPic.getBytes();
                return new SerialBlob(multiPartFileBytes);
            } catch (IOException | SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    public void validate() {
        if (roomNumber <= 0 || roomPrice == null || roomPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid room details");
        }
    }
}
