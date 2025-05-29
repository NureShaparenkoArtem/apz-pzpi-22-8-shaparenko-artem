package ua.service.SeStans.JWT.DTO;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}