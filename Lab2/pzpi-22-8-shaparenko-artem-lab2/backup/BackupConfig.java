package ua.service.SeStans.backup;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BackupConfig {
    @Bean
    @ConfigurationProperties(prefix = "backup.postgres")
    public BackupProperties backupProperties() {
        return new BackupProperties();
    }
}
