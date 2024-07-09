package dev.hoang.homehotel.room.model;

import dev.hoang.homehotel.room.dto.req.BookRoomRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BookedRoom {
    @Id
    @GeneratedValue
    private long id;
    private LocalDate bookedDate;
    private LocalDate returnDate;
    private int adultCount;
    private int childrenCount;
    private BigDecimal price;
    private String confirmationCode;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;


    public BookedRoom(BookRoomRequest bookRoomRequest, Room roomToBook, String confirmationCode) {
        this.room = roomToBook;
        this.bookedDate = bookRoomRequest.getBookDate();
        this.returnDate = bookRoomRequest.getReturnDate();
        this.adultCount = bookRoomRequest.getAdultCount();
        this.childrenCount = bookRoomRequest.getChildrenCount();
        this.confirmationCode = confirmationCode;
    }

}
