package dev.hoang.homehotel.room.dto.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoomResponse {
    private long id;
    private LocalDate bookedDate;
    private LocalDate returnDate;
    private int adultCount;
    private int childrenCount;
    private BigDecimal price;
    private long confirmationCode;
    private long roomId; // Assuming we only need the ID for the response

}

