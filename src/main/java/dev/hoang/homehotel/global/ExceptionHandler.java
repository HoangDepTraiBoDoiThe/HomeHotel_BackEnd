package dev.hoang.homehotel.global;

import dev.hoang.homehotel.room.exception.BookedRoomException;
import dev.hoang.homehotel.room.exception.RoomException;
import dev.hoang.homehotel.user.exception.UserException;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Data
@AllArgsConstructor
class ErrorDetail {
    String message;
    String webRequest;
}

@RestControllerAdvice
public class ExceptionHandler {
    String notfoundMessage= "Resource not found: ";

    @org.springframework.web.bind.annotation.ExceptionHandler(RoomException.class)
    ResponseEntity<?> handleRoomNotFound(RoomException ex, WebRequest webRequest) {
        String errorMessage= notfoundMessage + ex.getMessage();
        ErrorDetail errorDetail = new ErrorDetail(errorMessage, webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(BookedRoomException.class)
    ResponseEntity<?> handleBookedRoomNotFound(BookedRoomException ex, WebRequest webRequest) {
        String errorMessage= notfoundMessage + ex.getMessage();
        ErrorDetail errorDetail = new ErrorDetail(errorMessage, webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(UserException.class)
    public ResponseEntity<?> UserNotFound(UserException ex, WebRequest webRequest) {
        String errorMessage= notfoundMessage + ex.getMessage();
        ErrorDetail errorDetail = new ErrorDetail(errorMessage, webRequest.getDescription(true));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    public ResponseEntity<?> HandleAllException(Exception ex, WebRequest webRequest) {
        String errorMessage= "Internal Server Error: " + ex.getMessage();
        ErrorDetail errorDetail = new ErrorDetail(errorMessage, webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
