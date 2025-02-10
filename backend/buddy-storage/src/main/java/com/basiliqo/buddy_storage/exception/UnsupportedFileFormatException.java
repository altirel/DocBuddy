package com.basiliqo.buddy_storage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception occurs when detected unsupported file format.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsupportedFileFormatException extends RuntimeException {

    public UnsupportedFileFormatException(String fileName) {

        super(String.format("Unsupported file format for file '%s'.", fileName));
    }

}
