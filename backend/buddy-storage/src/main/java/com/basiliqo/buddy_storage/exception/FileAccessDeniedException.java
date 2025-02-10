package com.basiliqo.buddy_storage.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception occurs when user has no access to file.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class FileAccessDeniedException extends RuntimeException {

    public FileAccessDeniedException() {

        super("Access to the file is denied.");
    }

}
