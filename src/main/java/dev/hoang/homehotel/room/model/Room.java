package dev.hoang.homehotel.room.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import lombok.*;

import java.math.BigDecimal;
import java.sql.Blob;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue
    long id;
    int roomNumber;
    BigDecimal roomPrice;
    boolean isBooked;
    @Lob
    Blob roomPic;
}
