package org.example.core.repository;

public interface ILikeRepository {
    
        void toggleLike(long userId, long postId);
}
