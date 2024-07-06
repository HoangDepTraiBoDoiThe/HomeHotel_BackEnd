package dev.hoang.homehotel.room.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Data
@AllArgsConstructor
class  ErrorDetail {
    String message;
    String webRequest;
}

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(RoomException.class)
    ResponseEntity<?> handleMovieNotFound(RoomException ex, WebRequest webRequest) {
        String errorMessage= "Resource not found: " + ex.getMessage();
        ErrorDetail errorDetail = new ErrorDetail(errorMessage, webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
    ResponseEntity<?> handleAllException(Exception ex, WebRequest webRequest) {
        String errorMessage= "Internal Server Error: " + ex.getMessage();
        ErrorDetail errorDetail = new ErrorDetail(errorMessage, webRequest.getDescription(false));
        return new ResponseEntity<>(errorDetail, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
