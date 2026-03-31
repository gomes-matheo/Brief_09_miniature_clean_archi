package org.example.application.usecases;

import org.example.core.entities.User;
import org.example.core.exceptions.EntityAlreadyExistsException;
import org.example.core.repository.IUserRepository;

public class CreateUserCase {
    private final IUserRepository userRepo;

    public CreateUserCase(IUserRepository userRepository) {
        userRepo = userRepository;
    }

    private boolean isEmailAvailable(String email) {
        return userRepo.getUsers().stream().anyMatch(u -> u.getUsername().equals(email));
    }

    public void createUserSuccess (String username, String email, String password) {
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            throw new IllegalArgumentException("The registering form isn't complete !");
        } else if (!isEmailAvailable(email)) {
            throw new EntityAlreadyExistsException("This email already exists !");
        } else {
            new User(username, email, password);
        }
    }
}