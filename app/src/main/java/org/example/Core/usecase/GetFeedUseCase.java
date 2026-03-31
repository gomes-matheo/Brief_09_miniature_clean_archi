package org.example.Core.usecase;

import java.util.List;

import org.example.Core.repository.IntPostRepository;
import org.example.model.Post;

public class GetFeedUseCase {
    private final IntPostRepository postRepository;

    public GetFeedUseCase(IntPostRepository postRepository) {
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
