package ua.service.SeStans.backup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
@EnableScheduling
public class BackupService {

    private final BackupProperties properties;

    @Autowired
    public BackupService(BackupProperties properties) {
        this.properties = properties;
    }

    @Scheduled(cron = "0 0 2 * * *")
    public void scheduledBackup() {
        performBackup();
    }

    public boolean performBackup() {
        String backupFilePath = properties.getPath() + "backup.sql";
        File backupFile = new File(backupFilePath);

        if (backupFile.exists() && !backupFile.delete()) {
            System.err.println("Error while deleting old backup: " + backupFilePath);
            return false;
        }

        List<String> command = Arrays.asList(
                "pg_dump",
                "-U", properties.getUser(),
                "-h", properties.getHost(),
                "-p", properties.getPort(),
                "-F", "c",
                "-f", backupFilePath,
                properties.getDatabase()
        );

        ProcessBuilder pb = new ProcessBuilder(command);
        pb.environment().put("PGPASSWORD", properties.getPassword());

        try {
            Process process = pb.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Backup created: " + backupFilePath);
                return true;
            } else {
                System.err.println("Backup error. Exit code: " + exitCode);
                return false;
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return false;
        }
    }
}
