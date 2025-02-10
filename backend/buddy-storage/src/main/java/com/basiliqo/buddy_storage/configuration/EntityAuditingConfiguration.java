package com.basiliqo.buddy_storage.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * Entity auditing configuration. Required to support some base entity fields automatic management.
 */
@Configuration
@EnableJpaAuditing
public class EntityAuditingConfiguration {
}
