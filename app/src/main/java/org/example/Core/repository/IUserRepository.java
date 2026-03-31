package org.example.Core.repository;

import org.example.Core.entities.User;

import java.util.List;

public interface IUserRepository {
    void addUser(User user);
    void removeUser(User user);
    List<User> getUsers();
}
