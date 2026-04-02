package org.example.application.usecases;

import org.example.core.entities.Post;
import org.example.core.entities.User;
import org.example.core.repository.ILikeRepository;
import org.example.core.repository.IUserRepository;

public class LikeCase {
    private final ILikeRepository likedBy;
    private final IUserRepository userRepo;
    public LikeCase(ILikeRepository likeRepo, IUserRepository userRepository) {
        this.likedBy = likeRepo;
        this.userRepo = userRepository;
    }

    public void toggleLike(Post currentPost, long currentUserId) {
        userRepo.findById(currentUserId)
            .ifPresent(user -> {
            if (hasLiked(currentPost, user)) {
                likedBy.removeLike(currentPost, user);
            } else {
                likedBy.addLike(currentPost, user);
            }
        });
    }

    public boolean hasLiked(Post currentPost, User currentUser) {
        return likedBy.getUsersByLiked(currentPost).contains(currentUser);
    }

}
