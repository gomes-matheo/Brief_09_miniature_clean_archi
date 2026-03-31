package org.example.core.repository;

import java.util.List;
import java.util.Optional;

import org.example.core.entities.User;

    public interface IUserRepository {
    Optional<User> findByUsername(String username);
    void addUser(User user);
    void removeUser(User user);
    List<User> getUsers();
}
