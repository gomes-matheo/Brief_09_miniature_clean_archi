package org.example.application.usecases;

import java.util.Optional;

import org.example.core.entities.User;
import org.example.core.repository.IUserRepository;

public class AuthenticateUserCase {
    private final IUserRepository repository;

    public AuthenticateUserCase(IUserRepository repository) {
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
