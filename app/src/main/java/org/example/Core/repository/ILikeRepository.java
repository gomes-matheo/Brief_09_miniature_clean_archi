package org.example.core.repository;

import java.util.Set;

import org.example.core.entities.Post;
import org.example.core.entities.User;

public interface ILikeRepository {

    void addLike(Post currentPost, User currentUser);

    void removeLike(Post currentPost, User currentUser);

    int getLikes(Post currentPost);

    Set<User> getUsersByLiked(Post currenPost);
}
