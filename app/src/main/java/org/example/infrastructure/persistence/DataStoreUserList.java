package org.example.infrastructure.persistence;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.example.model.User;

public class DataStoreUserList {
    private List<User> users = new CopyOnWriteArrayList<>();

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
