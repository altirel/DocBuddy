package com.basiliqo.buddy_storage.service;

import com.basiliqo.buddy_storage.entity.File;
import com.basiliqo.buddy_storage.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * Service for {@link File}.
 */
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileRepository repository;

}
