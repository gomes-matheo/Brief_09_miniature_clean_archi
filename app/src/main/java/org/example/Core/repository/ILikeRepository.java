package org.example.Core.repository;

public interface ILikeRepository {
    
        void toggleLike(long userId, long postId);
}
