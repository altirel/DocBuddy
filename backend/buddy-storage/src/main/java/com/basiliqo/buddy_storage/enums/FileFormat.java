package com.basiliqo.buddy_storage.enums;

import com.basiliqo.buddy_storage.exception.UnsupportedFileFormatException;

/**
 * File format.
 */
public enum FileFormat {

    PDF(".pdf"),

    DOCX(".docx");

    private final String extension;

    FileFormat(String extension) {

        this.extension = extension;
    }

    /**
     * Detects file format by file name.
     *
     * @param fileName FIle name.
     * @return File format.
     *
     * @throws UnsupportedFileFormatException Occurs when file format not supported.
     */
    public static FileFormat detectFileFormat(String fileName) {

        for (FileFormat fileFormat : values()) {

            if (fileName.endsWith(fileFormat.extension)) {

                return fileFormat;
            }
        }

        throw new UnsupportedFileFormatException(fileName);
    }
}
