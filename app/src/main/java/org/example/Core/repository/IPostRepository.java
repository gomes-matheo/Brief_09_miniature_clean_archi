package org.example.core.repository;
import java.util.List;

import org.example.core.entities.Post;
import org.example.core.entities.User;

public interface IPostRepository {
    void save(User author, String content);
    void addPost(Post post);
    void removePost(Post post);
    List<Post> getPosts();
    List<Post> findAll();
    List<Post> findByFollowed(long userId);
}
