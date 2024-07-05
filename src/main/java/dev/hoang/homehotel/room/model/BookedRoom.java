package dev.hoang.homehotel.room.model;

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
    long id;
    LocalDate bookedDate;
    LocalDate returnDate;
    int adultCount;
    int childrenCount;
    BigDecimal price;
    long confirmationCode;

    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    Room roomId;
}
