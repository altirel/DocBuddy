package com.basiliqo.buddy_storage.exception;

import com.basiliqo.buddy_storage.dto.DetailedError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.UUID;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<DetailedError> handleTypeMismatchException(MethodArgumentTypeMismatchException e) {

        if (e.getRequiredType() != null && e.getRequiredType().equals(UUID.class)) {

            DetailedError error = DetailedError.of(
                HttpStatus.BAD_REQUEST,
                String.format("'%s' is not a valid UUID", e.getValue())
            );

            return ResponseEntity.badRequest().body(error);
        }

        return ResponseEntity.badRequest().build();
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<DetailedError> handleFileNotFoundException(FileNotFoundException e) {

        DetailedError error = DetailedError.of(HttpStatus.NOT_FOUND, e.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<DetailedError> handleFileException(FileException e) {

        DetailedError error = DetailedError.of(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(UnsupportedFileFormatException.class)
    public ResponseEntity<DetailedError> handleUnsupportedFileFormatException(UnsupportedFileFormatException e) {

        DetailedError error = DetailedError.of(HttpStatus.BAD_REQUEST, e.getMessage());

        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(FileAccessDeniedException.class)
    public ResponseEntity<DetailedError> handleFileAccessDeniedException(FileAccessDeniedException e) {

        DetailedError error = DetailedError.of(HttpStatus.FORBIDDEN, e.getMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(error);
    }

}
