package org.example.infrastructure.persistence;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

import org.example.core.entities.Post;
import org.example.core.entities.User;
import org.example.core.repository.IPostRepository;

public class PostRepository implements IPostRepository{
    
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

    @Override
    public List<Post> findAll() {
        return posts.stream()
            .filter(p -> !p.isDraft())
            .sorted(Comparator.comparing(Post::getCreatedAt).reversed())
            .collect(Collectors.toList());
    }

    @Override
    public List<Post> findByFollowed(long userId) {
        return List.of();
    }

    @Override
    public void save(User author, String content) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }
      
}
