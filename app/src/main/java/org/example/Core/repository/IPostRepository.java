package org.example.core.repository;
import java.util.List;
import java.util.Optional;

import org.example.core.entities.Post;
import org.example.core.entities.User;

public interface IPostRepository {
    void save(User author, String content);
    void addPost(Post post);
    void removePost(Post post);
    List<Post> getPostsFromFollowed(User subscribedUser);
    List<Post> getPostsFrom(User user);
    Optional<Post> getPostById(long id);
    List<Post> getPosts();
}
