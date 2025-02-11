package com.basiliqo.buddy_documents.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.Instant;
import java.util.UUID;


/**
 * Base entity with common fields.
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class BaseEntity {

    /**
     * ID.
     */
    @Id
    private UUID id;

    /**
     * The moment when entity was created.
     */
    @CreatedDate
    @Field(name = "created_at")
    private Instant createdAt;

    /**
     * The moment when entity was updated.
     */
    @LastModifiedDate
    @Field(name = "updated_at")
    private Instant updatedAt;

    /**
     * Entity version.
     */
    @Version
    private long version;

}
