package dev.hoang.homehotel.room.dto.res;

import dev.hoang.homehotel.room.model.BookedRoom;
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
    private String confirmationCode;
    private long roomId; // Assuming we only need the ID for the response

    public BookedRoomResponse(BookedRoom bookedRoom) {
        this.id = bookedRoom.getId();
        this.bookedDate = bookedRoom.getBookedDate();
        this.returnDate = bookedRoom.getReturnDate();
        this.adultCount = bookedRoom.getAdultCount();
        this.childrenCount = bookedRoom.getChildrenCount();
        this.price = bookedRoom.getPrice();
        this.confirmationCode = bookedRoom.getConfirmationCode();
        this.roomId = bookedRoom.getRoom().getId();
    }
}

