package dev.hoang.homehotel.room.dto.req;

import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomRequest {
    private int roomNumber;             // Non-null, unique
    private BigDecimal roomPrice;        // Non-null, positive
    @Lob
    private MultipartFile roomPic;       // Optional (for uploading a new picture)

    public void validate() {
        if (roomNumber <= 0 || roomPrice == null || roomPrice.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Invalid room details");
        }
    }
}
