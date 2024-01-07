package boki.tistory.userservice.controller;

import boki.tistory.userservice.domain.User;
import boki.tistory.userservice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/rest/v1/api/users")
@RestController
public class UserController {

    @Value("${server.port}")
    private String port;
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    public ResponseEntity<Object> getUsers() {
        List<User> users = this.userRepository.findAll();
        UserDTO userDTO = new UserDTO(port, users);
        return ResponseEntity.ok().body(userDTO);
    }
}

class UserDTO {
    public UserDTO(String serverPort, List<User> users) {
        this.serverPort = serverPort;
        this.users = users;
    }

    public String getServerPort() {
        return serverPort;
    }

    public List<User> getUsers() {
        return users;
    }

    private String serverPort;
    private List<User> users;
}
