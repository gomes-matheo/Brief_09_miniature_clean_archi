package org.example.infrastructure.persistence;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.example.Core.entities.User;
import org.example.Core.repository.IUserRepository;

public class UserRepository implements IUserRepository{
    private List<User> users = new ArrayList<>();
    private static final UserRepository USER_REPO_INSTANCE = new UserRepository();
    
    private UserRepository() {}

    public static UserRepository getInstance() {
        return USER_REPO_INSTANCE;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public List<User> getUsers() {
        return users;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByUsername'");
    }
}
