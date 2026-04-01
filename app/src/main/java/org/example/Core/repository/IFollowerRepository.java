package org.example.core.repository;

import java.util.Set;

import org.example.core.entities.User;

public interface IFollowerRepository {

    void addFollower(User currentUser, User userToFollow);

    void removeFollower(User currentUser, User userToUnfollow);

    Set<User> getFollows(User currentUser);

}