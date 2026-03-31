package org.example.Core.usecase;
import java.util.Optional;

import org.example.Core.repository.UserRepository;
import org.example.model.User;

public class AuthenticateUser {
    private final UserRepository repository;

    public AuthenticateUser(UserRepository repository) {
        this.repository = repository;
    }

    public Optional<User> execute(String username, String password) {
        Optional<User> user = repository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }
}
