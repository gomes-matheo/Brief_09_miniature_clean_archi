package org.example.infrastructure.persistence;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.example.model.Post;
import org.example.util.DataStore;


public class DataStorePostRepository{

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
