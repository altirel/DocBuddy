package com.basiliqo.buddy_storage.dto;

import com.basiliqo.buddy_storage.entity.File;
import com.basiliqo.buddy_storage.enums.FileFormat;

import java.time.Instant;
import java.util.UUID;

/**
 * Response with file details.
 *
 * @param id         File ID.
 * @param name       File name.
 * @param fileFormat File format.
 * @param size       File size.
 * @param created    Moment when file was created.
 */
public record FileResponse(UUID id, String name, FileFormat fileFormat, long size, Instant created) {

    public static FileResponse of(File file) {

        return new FileResponse(file.getId(), file.getName(), file.getFormat(), file.getSize(), file.getCreatedAt());
    }

}
