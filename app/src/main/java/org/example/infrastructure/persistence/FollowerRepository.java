package org.example.infrastructure.persistence;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.example.core.entities.User;
import org.example.core.repository.IFollowerRepository;

public class FollowerRepository implements IFollowerRepository{

    private Set<User> follows = Collections.synchronizedSet(new HashSet<>());
    private static final FollowerRepository FOLLOWER_REPO_INSTANCE = new FollowerRepository();

    private FollowerRepository() {}

    public static FollowerRepository getInstance() {
        return FOLLOWER_REPO_INSTANCE;
    }
    
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