package com.basiliqo.buddy_storage.dto;

import org.springframework.http.HttpStatus;

/**
 * Detailed error.
 *
 * @param httpStatusErrorCode HTTP status error code.
 * @param message             Detailed error message.
 */
public record DetailedError(int httpStatusErrorCode, String message) {

    public static DetailedError of(HttpStatus httpStatus, String message) {

        return new DetailedError(httpStatus.value(), message);
    }

}
