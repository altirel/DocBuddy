package com.basiliqo.buddy_storage.configuration;

import com.basiliqo.buddy_storage.clients.s3storage.HttpS3StorageClient;
import com.basiliqo.buddy_storage.clients.s3storage.NoopS3StorageClient;
import com.basiliqo.buddy_storage.clients.s3storage.S3StorageClient;
import com.basiliqo.buddy_storage.configuration.properties.S3ConfigurationProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

import java.net.URI;

/**
 * Configuration for {@link S3StorageClient}.
 */
@Configuration
public class S3Configuration {

    @Bean
    @Primary
    @ConditionalOnProperty(name = "s3.client.enabled", havingValue = "true")
    public S3StorageClient buddyS3StorageClient(S3ConfigurationProperties properties) {

        String bucket = properties.bucket();
        String providerName = properties.providerName();

        AwsBasicCredentials awsBasicCredentials = AwsBasicCredentials
            .create(properties.accessKey(), properties.secretKey());

        S3Client s3Client = S3Client.builder()
            .credentialsProvider(StaticCredentialsProvider.create(awsBasicCredentials))
            .region(Region.of(properties.region()))
            .endpointOverride(URI.create(properties.url()))
            .forcePathStyle(properties.pathStyle())
            .build();

        return new HttpS3StorageClient(bucket, providerName, s3Client);
    }

    @Bean
    @ConditionalOnProperty(name = "s3.client.enabled", havingValue = "false")
    public S3StorageClient noopS3StorageClient() {

        return new NoopS3StorageClient();
    }

}
