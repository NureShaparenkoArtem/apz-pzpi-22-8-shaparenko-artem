package ua.service.SeStans.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User createUser(String firstName, String lastName, String phone, String email, String password) {
        User user = new User(firstName, lastName, phone, email, password);

        return userRepository.save(user);
    }

    public User updateUser(Integer userId, String firstName, String lastName, String phone, String email, String password){
        User existingUser = userRepository.findById(userId).orElseThrow(() ->
                new IllegalArgumentException("User not found with ID:" + userId));

        existingUser.setFirst_name(firstName);
        existingUser.setLast_name(lastName);
        existingUser.setPhone(phone);
        existingUser.setEmail(email);
        existingUser.setPassword(password);

        return userRepository.save(existingUser);
    }

    public void deleteUser(Integer userId){
        if (!userRepository.existsById(userId)){
            throw new IllegalArgumentException("User not found with ID:" + userId);
        }
        userRepository.deleteById(userId);
    }
}
