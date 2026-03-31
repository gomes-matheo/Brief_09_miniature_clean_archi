package org.example.application.usecases;

import java.util.List;

import org.example.core.entities.Post;
import org.example.core.repository.IPostRepository;

public class RetrieveFeedCase {
    private final IPostRepository postRepository;

    public RetrieveFeedCase(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> execute(long userId, String mode) {
        return postRepository.getPosts();
    }

    public List<Post> execute(String mode) {
        List<Post> posts = postRepository.getPosts();
        if ("abonnements".equals(mode)) {
            posts = postRepository.getPosts();
        } else {
            posts = postRepository.getPosts();
        }
        return posts;
    }

}
