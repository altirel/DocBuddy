package com.basiliqo.buddy_storage.entity;

import com.basiliqo.buddy_storage.enums.FileFormat;
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
 * Stored file details.
 */
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Table(name = "file")
public class File extends BaseEntity {

    /**
     * File name.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * File owner ID.
     */
    @Column(name = "owner_id", nullable = false)
    private UUID ownerId;

    /**
     * File format.
     */
    @Column(name = "format", nullable = false)
    private FileFormat format;

    /**
     * File size in bytes.
     */
    @Column(name = "size", nullable = false)
    private Long size;

    /**
     * File content hash.
     */
    @Column(name = "hash", nullable = false)
    private String hash;

}
