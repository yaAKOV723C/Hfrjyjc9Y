// 代码生成时间: 2025-09-23 11:02:55
package com.example.demo;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "config")
public class ConfigManager {

    private Environment environment;

    public ConfigManager(Environment environment) {
        this.environment = environment;
    }

    /**
     * Retrieves a configuration property value.
     * @param key The key of the property.
     * @return The value of the property or null if not found.
     */
    public String getProperty(String key) {
        try {
            return environment.getProperty(key);
        } catch (Exception e) {
            // Error handling can be more sophisticated based on requirements.
            // For now, we simply log the error and return null.
            System.err.println("Error retrieving property: " + key + " - " + e.getMessage());
            return null;
        }
    }

    /**
     * Retrieves a configuration property value that is expected to be an integer.
     * @param key The key of the property.
     * @return The integer value of the property or null if not found or conversion fails.
     */
    public Integer getIntProperty(String key) {
        String value = getProperty(key);
        if (value != null) {
            try {
                return Integer.parseInt(value);
            } catch (NumberFormatException e) {
                System.err.println("Error parsing integer property: " + key + " - " + e.getMessage());
            }
        }
        return null;
    }

    // Add more methods for other data types as needed.

    // Getters and setters for any specific configuration properties can be added here.

}
