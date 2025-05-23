package ua.service.SeStans.backup;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Component
public class BackupProperties {
    private String host;
    private String port;
    private String user;
    private String password;
    private String database;
    private String path;

}
