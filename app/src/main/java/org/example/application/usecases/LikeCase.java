package org.example.application.usecases;

import org.example.core.entities.Post;
import org.example.core.entities.User;
import org.example.core.repository.ILikeRepository;

public class LikeCase {
    private final ILikeRepository likedBy;

    public LikeCase(ILikeRepository likeRepo) {
        this.likedBy = likeRepo;
    }
    
    public void toggleLike(Post currentPost, User currentUser) {
        if (hasLiked(currentPost ,currentUser)) {
            likedBy.removeLike(currentPost, currentUser);
        } else {
            likedBy.addLike(currentPost, currentUser);
        }
    }

    public boolean hasLiked(Post currentPost, User currentUser) {
        return likedBy.getUsersByLiked(currentPost).contains(currentUser);
    }

}
