package com.basiliqo.buddy_storage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception occurs when there are issues with the file.
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class FileException extends RuntimeException {

    public FileException(String message, Throwable cause) {

        super(message, cause);
    }

}
