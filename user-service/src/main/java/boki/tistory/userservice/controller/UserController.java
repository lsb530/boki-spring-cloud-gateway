package boki.tistory.userservice.controller;

import boki.tistory.userservice.domain.User;
import boki.tistory.userservice.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/rest/v1/api/users")
@RestController
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<Object> getUsers() {
        List<User> users = this.userRepository.findAll();
        System.out.println(users);
        return ResponseEntity.ok().body(users);
    }
}
