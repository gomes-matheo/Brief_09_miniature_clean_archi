package org.example.Core.repository;

import java.util.Optional;
import java.util.List;
import org.example.model.User;

public interface UserRepository {
    Optional<User> findByUsername(String username);

    void addUser(User user);
    void removeUser(User user);
    List<User> getUsers();
}
