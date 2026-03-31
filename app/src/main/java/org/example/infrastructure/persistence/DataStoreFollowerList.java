package org.example.infrastructure.persistence;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.example.model.User;

public class DataStoreFollowerList {

    private Set<User> follows = Collections.synchronizedSet(new HashSet<>());

        public void addFollower(User user) {
        follows.add(user);
    }

    public void removeFollower(User user) {
        follows.remove(user);
    }

    public Set<User> getFollows() {
        return follows;
    }
}