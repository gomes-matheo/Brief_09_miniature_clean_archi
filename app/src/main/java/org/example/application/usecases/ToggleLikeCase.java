package org.example.application.usecases;

import org.example.core.repository.ILikeRepository;

public class ToggleLikeCase {
    private final ILikeRepository likeRepository;

    public ToggleLikeCase(ILikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void execute(long userId, long postId) {
        likeRepository.toggleLike(userId, postId);
    }
}
