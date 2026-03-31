package org.example.infrastructure.persistence;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import org.example.Core.entities.Post;

public class PostRepository {
    
    private List<Post> posts = new CopyOnWriteArrayList<>();
    private static final PostRepository POST_REPO_INSTANCE = new PostRepository();

    private PostRepository() {}

    public static PostRepository getInstance() {
        return POST_REPO_INSTANCE;
    }

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
