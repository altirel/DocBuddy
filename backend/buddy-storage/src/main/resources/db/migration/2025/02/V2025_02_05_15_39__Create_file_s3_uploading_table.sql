CREATE TABLE file_s3_uploading
(
    id               UUID                     NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    file_id          UUID                     NOT NULL,
    s3_provider_name TEXT                     NOT NULL,
    key              TEXT                     NOT NULL,
    bucket           TEXT                     NOT NULL,
    created_at       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at       TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    version          BIGINT                   NOT NULL DEFAULT 1
);

ALTER TABLE file_s3_uploading
    ADD CONSTRAINT fk_file_s3_uploading__file_id___file__id FOREIGN KEY (file_id) REFERENCES file (id);