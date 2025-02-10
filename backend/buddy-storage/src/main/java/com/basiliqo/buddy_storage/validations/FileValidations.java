package com.basiliqo.buddy_storage.validations;

import com.basiliqo.buddy_storage.exception.FileAccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * Validations kit to verify data consistency and access rights.
 */
@Component
public class FileValidations {

    /**
     * Checks if user is file owner.
     *
     * @param ownerId File owner ID.
     * @param userId  User ID.
     *
     * @throws FileAccessDeniedException If user ID and file owner ID are different.Œ
     */
    public void requireUserIsFileOwner(UUID ownerId, UUID userId) {

        if (!ownerId.equals(userId)) {

            throw new FileAccessDeniedException();
        }
    }

}
