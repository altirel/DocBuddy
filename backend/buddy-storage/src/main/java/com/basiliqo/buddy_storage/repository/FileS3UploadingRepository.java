package com.basiliqo.buddy_storage.repository;

import com.basiliqo.buddy_storage.entity.FileS3Uploading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository for {@link FileS3Uploading}.
 */
public interface FileS3UploadingRepository extends JpaRepository<FileS3Uploading, UUID> {

}
