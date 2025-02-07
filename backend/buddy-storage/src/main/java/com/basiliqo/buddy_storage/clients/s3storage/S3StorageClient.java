package com.basiliqo.buddy_storage.clients.s3storage;

/**
 * Client for interaction with S3 storage.
 */
public interface S3StorageClient {

    /**
     * Downloads file from S3 storage.
     *
     * @param bucket bucket.
     * @param key    key.
     *
     * @return file content.
     */
    byte[] download(String bucket, String key);

    /**
     * Uploads file to S3 storage.
     *
     * @param key     key.
     * @param content content.
     */
    void upload(String key, byte[] content);

    /**
     * Deletes file from S3 storage.
     *
     * @param key key.
     */
    void delete(String key);

    /**
     * Returns bucket name.
     *
     * @return Bucket name.
     */
    String getBucket();

    /**
     * Returns S3 provider name.
     *
     * @return S3 provider name.
     */
    String getProviderName();

}
