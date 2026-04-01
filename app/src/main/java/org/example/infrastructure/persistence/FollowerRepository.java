package org.example.infrastructure.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.example.core.entities.User;
import org.example.core.repository.IFollowerRepository;

public class FollowerRepository implements IFollowerRepository{

    private Map<User, Set<User>> followers = new HashMap<>();

    public FollowerRepository() {
        
    }
    
    @Override
    public void addFollower(User currentUser, User userToFollow) {
        followers.get(currentUser).add(userToFollow);
    }
    
    @Override
    public void removeFollower(User currentUser, User userToUnfollow) {
        followers.get(currentUser).remove(userToUnfollow);
    }

    @Override
    public Set<User> getFollows(User currentUser) {
        return followers.get(currentUser);
    }
}