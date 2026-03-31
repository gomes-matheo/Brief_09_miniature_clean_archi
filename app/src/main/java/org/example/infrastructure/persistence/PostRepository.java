package org.example.infrastructure.persistence;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.example.Core.entities.Post;

public class PostRepository {
    private List<Post> posts = new CopyOnWriteArrayList<>();

    public void addPost(Post post) {
        posts.add(post);
    }

    public void removePost(Post post) {
        posts.remove(post);
    }

    public List<Post> getPosts() {
        return posts;
    }
}