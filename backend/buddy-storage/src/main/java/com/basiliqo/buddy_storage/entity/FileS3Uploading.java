package com.basiliqo.buddy_storage.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * A record about file stored in S3 storage.
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "file_s3_uploading")
public class FileS3Uploading extends BaseEntity {

    /**
     * File's ID. {@link File#getId()}.
     */
    @Column(name = "file_id")
    private final UUID fileId;

    /**
     * S3 Provider name.
     */
    @Column(name = "s3_provider_name")
    private final String s3ProviderName;

    /**
     * File key in storage.
     */
    private final String key;

    /**
     * Bucket where file stores.
     */
    private final String bucket;

}
