package org.example.Core.repository;
import org.example.model.User;
import org.example.model.Post;
import java.util.List;

public interface IntPostRepository {
    void save(User author, String content);
    void addPost(Post post);
    void removePost(Post post);
    List <Post> getPosts();
}
