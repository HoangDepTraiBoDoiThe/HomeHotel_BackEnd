package dev.hoang.homehotel.room.exception;

public class BookedRoomException extends RuntimeException {
    private String exceptionMessage;

    public BookedRoomException(String message) {
        super(message);
    }
}
