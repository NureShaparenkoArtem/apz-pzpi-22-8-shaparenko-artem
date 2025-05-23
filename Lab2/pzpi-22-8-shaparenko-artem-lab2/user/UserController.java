package ua.service.SeStans.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/user")
//@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PostMapping
    public User createUser(@RequestParam String first_name,
                           @RequestParam String last_name,
                           @RequestParam String phone,
                           @RequestParam String email,
                           @RequestParam String password){
        return userService.createUser(first_name, last_name, phone, email, password);
    }

    @PutMapping(path = "/{userId}")
    public User updateUser(@PathVariable Integer userId,
                           @RequestParam String first_name,
                           @RequestParam String last_name,
                           @RequestParam String phone,
                           @RequestParam String email,
                           @RequestParam String password){
        return userService.updateUser(userId, first_name, last_name, phone, email, password);
    }

    @DeleteMapping(path = "/{userId}")
    public void deleteUser(@PathVariable Integer userId){
        userService.deleteUser(userId);
    }
}
