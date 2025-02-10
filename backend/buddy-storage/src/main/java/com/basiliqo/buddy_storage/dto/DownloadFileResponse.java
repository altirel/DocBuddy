package com.basiliqo.buddy_storage.dto;

import org.springframework.core.io.Resource;

/**
 * Response with downloaded file and file name.
 *
 * @param fileName
 * @param file
 */
public record DownloadFileResponse(String fileName, Resource file) {

    public static DownloadFileResponse of(String fileName, Resource file) {

        return new DownloadFileResponse(fileName, file);
    }

}
