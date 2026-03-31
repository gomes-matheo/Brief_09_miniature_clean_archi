package org.example.Core.repository;

import org.example.Core.entities.User;

import java.util.List;
import java.util.Optional;

    public interface IUserRepository {
    Optional<User> findByUsername(String username);
    void addUser(User user);
    void removeUser(User user);
    List<User> getUsers();
}
