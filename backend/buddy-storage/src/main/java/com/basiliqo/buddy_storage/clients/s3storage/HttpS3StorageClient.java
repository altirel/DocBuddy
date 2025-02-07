package com.basiliqo.buddy_storage.clients.s3storage;

import com.basiliqo.buddy_storage.exception.FileException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;

/**
 * <p>Configured S3 client.</p>
 */
@Slf4j
@RequiredArgsConstructor
public class HttpS3StorageClient implements S3StorageClient {

    @Getter
    private final String bucket;

    @Getter
    private final String providerName;

    private final S3Client s3Client;

    @Override
    public byte[] download(String key, String bucket) {

        try {

            GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

            return s3Client.getObject(request).readAllBytes();
        } catch (IOException e) {

            log.error("Error downloading file", e);
            throw new FileException("Error occurred while downloading file", e);
        }
    }

    @Override
    public void upload(String key, byte[] content) {

        try {

            PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

            s3Client.putObject(request, RequestBody.fromBytes(content));
        } catch (Exception e) {

            log.error("Error uploading file", e);
            throw new FileException("Error occurred while uploading file", e);
        }
    }

    @Override
    public void delete(String key) {

        try {

            DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucket)
                .key(key)
                .build();

            s3Client.deleteObject(request);
        } catch (Exception e) {

            log.error("Error deleting file", e);
            throw new FileException("Error occurred while deleting file", e);
        }
    }

}
