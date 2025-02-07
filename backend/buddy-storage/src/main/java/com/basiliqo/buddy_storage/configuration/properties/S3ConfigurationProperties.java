package com.basiliqo.buddy_storage.configuration.properties;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>S3 client configuration.</p>
 *
 * @param providerName Provider name
 * @param url       URL
 * @param bucket    Bucket
 * @param accessKey Access key
 * @param secretKey Secret key
 * @param region    Region
 */
@ConfigurationProperties(prefix = "s3.client")
public record S3ConfigurationProperties(
    @NotBlank String providerName,
    @NotBlank String url,
    @NotBlank String bucket,
    @NotBlank String accessKey,
    @NotBlank String secretKey,
    @NotBlank String region
) {

}
