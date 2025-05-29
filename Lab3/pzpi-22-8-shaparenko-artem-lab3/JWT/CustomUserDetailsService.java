package ua.service.SeStans.JWT;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public UserDetails loadUserByUsername(String username) {
        if ("administrator".equals(username)) {
            String passwordFromDb = passwordEncoder.encode("administrator");

            List<String> rolesFromDb = List.of("ADMINISTRATOR");

            return new CustomUserDetails(username, passwordFromDb, rolesFromDb);
        }

        throw new UsernameNotFoundException("User not found");
    }
}
