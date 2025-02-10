package com.basiliqo.buddy_storage.repository;

import com.basiliqo.buddy_storage.entity.FileS3Uploading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

/**
 * Repository for {@link FileS3Uploading}.
 */
public interface FileS3UploadingRepository extends JpaRepository<FileS3Uploading, UUID> {

    /**
     * Finds record about file uploading in S3 storage by file ID.
     *
     * @param fileId File ID.
     *
     * @return Record about file uploading in S3 storage.
     */
    Optional<FileS3Uploading> findByFileId(UUID fileId);

    /**
     * Deletes file uploading record by file ID.
     *
     * @param fileId File ID.
     */
    void deleteByFileId(UUID fileId);

}
