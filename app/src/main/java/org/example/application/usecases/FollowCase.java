package org.example.application.usecases;

import org.example.core.entities.User;
import org.example.core.repository.IFollowerRepository;

public class FollowCase {
    private final IFollowerRepository followerRepo;

    public FollowCase(IFollowerRepository followerRepository) {
        this.followerRepo = followerRepository;
    }

    public boolean isFollowing(User currentUser, User toFollow) {
        return followerRepo.getFollows(currentUser).contains(toFollow);
    }
}