package com.basiliqo.buddy_storage.service;

import com.basiliqo.buddy_storage.entity.FileS3Uploading;
import com.basiliqo.buddy_storage.exception.FileNotFoundException;
import com.basiliqo.buddy_storage.repository.FileS3UploadingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * Service for {@link FileS3Uploading}.
 */
@Service
@RequiredArgsConstructor
public class FileS3UploadingService {

    private final FileS3UploadingRepository repository;

    /**
     * Creates new record about file stored in S3 storage.
     *
     * @param fileId       File ID.
     * @param bucket       Bucket.
     * @param key          File key.
     * @param providerName S3 Provider name.
     *
     * @return Record about file stored in S3 storage.
     */
    public FileS3Uploading create(UUID fileId, String bucket, String key, String providerName) {

        FileS3Uploading fileS3Uploading = FileS3Uploading.builder()
            .fileId(fileId)
            .bucket(bucket)
            .key(key)
            .s3ProviderName(providerName)
            .build();

        return repository.save(fileS3Uploading);
    }

    /**
     * Returns record about file uploading in S3 storage by file ID.
     *
     * @param fileId File ID.
     *
     * @return Record about file uploading in S3 storage.
     *
     * @throws FileNotFoundException If record not found.
     */
    public FileS3Uploading getByFileId(UUID fileId) {

        return repository.findByFileId(fileId).orElseThrow(() -> new FileNotFoundException(fileId));
    }

    /**
     * Deletes file uploading record by file ID.
     *
     * @param fileId File ID.
     */
    public void deleteByFileId(UUID fileId) {

        repository.deleteByFileId(fileId);
    }

}
