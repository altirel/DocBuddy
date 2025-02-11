package com.basiliqo.buddy_documents.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

/**
 * Entity auditing configuration. Required to support some base entity fields automatic management.
 */
@Configuration
@EnableMongoAuditing
public class MongoAuditConfiguration {

}
