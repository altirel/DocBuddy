# DocBuddy. Storage

## Description

This service allows users to store template and document files, enabling downloads at any time.

## Requirements

This service requires that you have:

1. A PostgreSQL database
2. An S3 storage server (locally or in the cloud).

## Launch service

### Docker

To build and start all required services in detached mode, run:

```
docker compose up -d
```

The bucket might not be created on the first attempt, so a manual service restart may be required.
To manually create the bucket, execute the following command:

```
docker restart minio-mc
```

### IDE

1. Open the project in your favorite IDE.
2. Set all required variables in the Run Configuration.
3. Run the application.