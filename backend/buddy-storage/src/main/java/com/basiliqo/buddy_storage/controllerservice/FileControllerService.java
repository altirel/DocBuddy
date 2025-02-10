package com.basiliqo.buddy_storage.controllerservice;

import com.basiliqo.buddy_storage.clients.s3storage.S3StorageClient;
import com.basiliqo.buddy_storage.dto.DownloadFileResponse;
import com.basiliqo.buddy_storage.dto.FileResponse;
import com.basiliqo.buddy_storage.entity.File;
import com.basiliqo.buddy_storage.entity.FileS3Uploading;
import com.basiliqo.buddy_storage.enums.FileFormat;
import com.basiliqo.buddy_storage.exception.FileException;
import com.basiliqo.buddy_storage.service.FileS3UploadingService;
import com.basiliqo.buddy_storage.service.FileService;
import com.basiliqo.buddy_storage.validations.FileValidations;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileControllerService {

    private final FileService fileService;

    private final FileS3UploadingService fileS3UploadingService;

    private final FileValidations fileValidations;

    private final S3StorageClient s3StorageClient;

    /**
     * Returns file details.
     *
     * @param fileId File ID.
     * @param userId User ID.
     *
     * @return File Details.
     */
    public FileResponse getFile(UUID fileId, UUID userId) {

        File file = fileService.getById(fileId);

        fileValidations.requireUserIsFileOwner(file.getOwnerId(), userId);

        return FileResponse.of(file);
    }

    /**
     * Downloads file from S3 storage.
     *
     * @param fileId File ID.
     * @param userId User ID.
     *
     * @return Downloaded file content as {@link Resource}.
     */
    public DownloadFileResponse download(UUID fileId, UUID userId) {

        File file = fileService.getById(fileId);

        fileValidations.requireUserIsFileOwner(file.getOwnerId(), userId);

        FileS3Uploading uploading = fileS3UploadingService.getByFileId(fileId);
        byte[] content = s3StorageClient.download(uploading.getBucket(), uploading.getKey());

        return DownloadFileResponse.of(file.getName(), new ByteArrayResource(content));
    }

    /**
     * Uploads file to S3 storage. Also creates records about stored file metadata and records about what storage was
     * used to save files.
     *
     * @param multipartFile Uploaded file.
     * @param userId        User ID.
     *
     * @return Uploaded file details.
     */
    @Transactional
    public FileResponse upload(MultipartFile multipartFile, UUID userId) {

        try {
            String fileName = multipartFile.getOriginalFilename();
            FileFormat fileFormat = FileFormat.detectFileFormat(fileName);
            byte[] content = multipartFile.getBytes();
            String hash = DigestUtils.md5DigestAsHex(content);
            File file = fileService.create(fileName, userId, fileFormat, content.length, hash);

            UUID fileId = file.getId();
            String key = fileId.toString();
            String bucket = s3StorageClient.getBucket();
            String providerName = s3StorageClient.getProviderName();
            fileS3UploadingService.create(fileId, bucket, key, providerName);

            s3StorageClient.upload(key, content);

            return FileResponse.of(file);
        } catch (IOException e) {

            throw new FileException("Error occurred while uploading file", e);
        }
    }

    /**
     * Deletes file from S3 storage and record about stored file.
     *
     * @param fileId File ID.
     * @param userId User ID.
     */
    @Transactional
    public void delete(UUID fileId, UUID userId) {

        File file = fileService.getById(fileId);
        String key = fileS3UploadingService.getByFileId(fileId).getKey();

        fileValidations.requireUserIsFileOwner(file.getOwnerId(), userId);

        fileS3UploadingService.deleteByFileId(fileId);
        fileService.deleteById(fileId);

        s3StorageClient.delete(key);
    }

}
