package org.example.Core.usecase;

import org.example.Core.repository.IntLikeRepository;

public class ToggleLikeUseCase {
    private final IntLikeRepository likeRepository;

    public ToggleLikeUseCase(IntLikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void execute(long userId, long postId) {
        likeRepository.toggleLike(userId, postId);
    }
}
