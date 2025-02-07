package com.basiliqo.buddy_storage.repository;

import com.basiliqo.buddy_storage.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Repository for {@link File}.
 */
public interface FileRepository extends JpaRepository<File, UUID> {

}
