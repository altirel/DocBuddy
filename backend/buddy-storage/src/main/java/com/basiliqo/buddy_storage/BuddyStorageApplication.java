package com.basiliqo.buddy_storage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan("com.basiliqo.buddy_storage.configuration.properties")
public class BuddyStorageApplication {

    public static void main(String[] args) {
        SpringApplication.run(BuddyStorageApplication.class, args);
    }

}
