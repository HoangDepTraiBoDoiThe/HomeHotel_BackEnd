package dev.hoang.homehotel.room.exception;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RoomException extends RuntimeException {
    private String exceptionMessage;

    public RoomException(String message) {
        super(message);
    }
}
