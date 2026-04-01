package org.example.infrastructure.persistence;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.example.core.entities.Post;
import org.example.core.entities.User;
import org.example.core.repository.ILikeRepository;

public class LikeRepository implements ILikeRepository {
    private Map<Post, Set<User>> likes = new HashMap<>();

    public LikeRepository() {}

    @Override
    public void addLike(Post currentPost, User currentUser) {
        likes.get(currentPost).add(currentUser);
    }

    @Override
    public void removeLike(Post currentPost, User currentUser) {
        likes.get(currentPost).remove(currentUser);
    }

    @Override
    public int getLikes(Post currentPost) {
        return likes.get(currentPost).size();
    }

    @Override
    public Set<User> getUsersByLiked(Post currentPost) {
        return likes.get(currentPost);
    }
}