package com.basiliqo.buddy_storage.service;

import com.basiliqo.buddy_storage.entity.File;
import com.basiliqo.buddy_storage.enums.FileFormat;
import com.basiliqo.buddy_storage.exception.FileNotFoundException;
import com.basiliqo.buddy_storage.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service for {@link File}.
 */
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository repository;

    /**
     * Creates new stored file details.
     *
     * @param name    File name.
     * @param ownerId File owner's ID.
     * @param format  File format.
     * @param size    file size.
     * @param hash    MD5 hash as hex.
     *
     * @return Stored file details.
     */
    public File create(String name, UUID ownerId, FileFormat format, long size, String hash) {

        File file = File.builder()
            .name(name)
            .ownerId(ownerId)
            .format(format)
            .size(size)
            .hash(hash)
            .build();

        return repository.save(file);
    }

    /**
     * Returns file by file ID.
     *
     * @param fileId File ID.
     *
     * @return File.
     */
    public File getById(UUID fileId) {

        return repository.findById(fileId).orElseThrow(() -> new FileNotFoundException(fileId));
    }

    /**
     * Deletes file by ID.
     *
     * @param fileId File ID.
     */
    public void deleteById(UUID fileId) {

        repository.deleteById(fileId);
    }

}
