package ua.service.SeStans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ua.service.SeStans.backup.BackupProperties;

@SpringBootApplication
//@EnableConfigurationProperties(BackupProperties.class)
public class SeStansApplication {

	public static void main(String[] args) {
		SpringApplication.run(SeStansApplication.class, args);
	}

}
