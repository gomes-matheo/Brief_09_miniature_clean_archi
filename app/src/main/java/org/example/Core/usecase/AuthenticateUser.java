package org.example.Core.usecase;
import java.util.Optional;

import org.example.Core.repository.IntUserRepository;
import org.example.model.User;

public class AuthenticateUser {
    private final IntUserRepository repository;

    public AuthenticateUser(IntUserRepository repository) {
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
