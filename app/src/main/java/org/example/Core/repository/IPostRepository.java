package org.example.Core.repository;
import java.util.List;

import org.example.Core.entities.Post;
import org.example.Core.entities.User;

public interface IPostRepository {
    void save(User author, String content);
    void addPost(Post post);
    void removePost(Post post);
    List <Post> getPosts();
}
