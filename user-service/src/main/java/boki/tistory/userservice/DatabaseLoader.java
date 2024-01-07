package boki.tistory.userservice;

import boki.tistory.userservice.domain.User;
import boki.tistory.userservice.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final UserRepository userRepository;

    public DatabaseLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) {
        userRepository.save(new User("Kim", 20));
        userRepository.save(new User("Lee", 32));
    }
}
