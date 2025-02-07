CREATE TABLE file
(
    id         UUID                     NOT NULL DEFAULT uuid_generate_v4() PRIMARY KEY,
    name       TEXT                     NOT NULL,
    owner_id   UUID                     NOT NULL,
    format     TEXT                     NOT NULL,
    size       BIGINT                   NOT NULL,
    hash       TEXT                     NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    updated_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT NOW(),
    version    BIGINT                   NOT NULL DEFAULT 1
);

ALTER TABLE file
    ADD CONSTRAINT ck_file_format_check CHECK (format IN ('PDF', 'DOCX'));