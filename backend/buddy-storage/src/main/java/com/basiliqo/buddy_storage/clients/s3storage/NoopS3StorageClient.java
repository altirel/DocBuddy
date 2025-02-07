package com.basiliqo.buddy_storage.clients.s3storage;

import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(force = true)
public class NoopS3StorageClient implements S3StorageClient {

    private static final String BUCKET = "noop";

    private static final String PROVIDER_NAME = "noop/docbuddy";

    @Override
    public byte[] download(String bucket, String key) {

        log.debug("Download file from bucket: '{}', with key: '{}'", bucket, key);

        return new byte[0];
    }

    @Override
    public void upload(String key, byte[] content) {

        log.debug("Upload file with key: '{}'", key);
    }

    @Override
    public void delete(String key) {

        log.debug("Delete file with key: '{}'", key);
    }

    @Override
    public String getBucket() {

        return BUCKET;
    }

    @Override
    public String getProviderName() {

        return PROVIDER_NAME;
    }

}
