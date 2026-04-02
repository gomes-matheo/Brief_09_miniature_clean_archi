package org.example.infrastructure.persistence;

import java.util.List;
import java.util.Optional;

import org.example.core.entities.User;
import org.example.core.repository.IUserRepository;

import java.util.ArrayList;

public class UserRepository implements IUserRepository{
    
    private List<User> users = new ArrayList<>();
    private static long id;

    public UserRepository() {}
    
    @Override
    public void addUser(User user) {
        users.add(user);
        id ++;
        user.setId(id);
    }

    @Override
    public void removeUser(User user) {
        users.remove(user);
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return users.stream().filter(u -> u.getUsername().equals(username)).findFirst();
    }

    @Override
    public Optional<User> findById(long id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }
}
