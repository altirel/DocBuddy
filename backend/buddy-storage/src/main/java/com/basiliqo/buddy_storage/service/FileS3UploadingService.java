package com.basiliqo.buddy_storage.service;

import com.basiliqo.buddy_storage.entity.FileS3Uploading;
import com.basiliqo.buddy_storage.repository.FileS3UploadingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for {@link FileS3Uploading}.
 */
@Service
@RequiredArgsConstructor
public class FileS3UploadingService {

    private final FileS3UploadingRepository repository;

}
