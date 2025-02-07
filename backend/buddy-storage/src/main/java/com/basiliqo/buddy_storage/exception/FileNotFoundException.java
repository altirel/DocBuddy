package com.basiliqo.buddy_storage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

/**
 * An exception occurs when file not found.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FileNotFoundException extends RuntimeException {

    public FileNotFoundException(UUID fileId) {

        super(String.format("File '%s' not found.", fileId));
    }

}
