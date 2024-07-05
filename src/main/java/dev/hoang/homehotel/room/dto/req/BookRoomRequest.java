package dev.hoang.homehotel.room.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRoomRequest {
    private LocalDate bookedDate; // Non-null
    private LocalDate returnDate;  // Non-null, after bookedDate
    private int adultCount;       // >= 0
    private int childrenCount;    // >= 0
    private long roomId;

    public void validate() {
        if (bookedDate == null || returnDate == null || bookedDate.isAfter(returnDate) || adultCount < 0 || childrenCount < 0 || roomId <= 0) {
            throw new IllegalArgumentException("Invalid booking details");
        }
    }
}

