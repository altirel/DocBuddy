package com.basiliqo.buddy_storage.controller;

import com.basiliqo.buddy_storage.controllerservice.FileControllerService;
import com.basiliqo.buddy_storage.dto.DownloadFileResponse;
import com.basiliqo.buddy_storage.dto.FileResponse;
import com.basiliqo.buddy_storage.exception.FileException;
import com.basiliqo.buddy_storage.utils.RequestHeaders;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.UUID;

/**
 * Controller for operations with file.
 */
@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
public class FileController {

    private final FileControllerService fileControllerService;

    /**
     * Retrieves file details.
     *
     * @param fileId File ID.
     * @param userId User ID.
     *
     * @return File details.
     */
    @GetMapping("/{fileId}")
    public ResponseEntity<FileResponse> getFile(
        @PathVariable UUID fileId,
        @RequestHeader(RequestHeaders.USER_ID) UUID userId
    ) {

        return ResponseEntity.ok(fileControllerService.getFile(fileId, userId));
    }

    /**
     * <p>Downloads file.</p>
     *
     * @param fileId File ID.
     * @param userId User ID.
     *
     * @return File content.
     */
    @GetMapping("/{fileId}/download")
    public ResponseEntity<Resource> download(
        @PathVariable UUID fileId,
        @RequestHeader(RequestHeaders.USER_ID) UUID userId
    ) {

        try {
            DownloadFileResponse response = fileControllerService.download(fileId, userId);

            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + response.fileName() + "\"")
                .contentLength(response.file().getContentAsByteArray().length)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(response.file());
        } catch (IOException e) {

            throw new FileException("Error occurred while forming up file content response.", e);
        }
    }

    /**
     * Uploads new file to storage.
     *
     * @param file   File content
     * @param userId User ID.
     *
     * @return File details.
     */
    @PostMapping
    public ResponseEntity<FileResponse> upload(
        @RequestBody MultipartFile file,
        @RequestHeader(RequestHeaders.USER_ID) UUID userId
    ) {

        FileResponse response = fileControllerService.upload(file, userId);

        URI location = UriComponentsBuilder.fromUriString("/api/v1/files/{fileId}")
            .buildAndExpand(response.id())
            .toUri();

        return ResponseEntity.created(location).body(response);
    }

    /**
     * Deletes file.
     *
     * @param fileId File ID.
     * @param userId User ID.
     *
     * @return No content HTTP-code.
     */
    @DeleteMapping("/{fileId}")
    public ResponseEntity<Void> deleteFile(
        @PathVariable UUID fileId,
        @RequestHeader(RequestHeaders.USER_ID) UUID userId
    ) {

        fileControllerService.delete(fileId, userId);

        return ResponseEntity.noContent().build();
    }

}
