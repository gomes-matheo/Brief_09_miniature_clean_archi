package org.example.application.usecases;

import java.util.List;

import org.example.core.entities.Post;
import org.example.core.entities.User;
import org.example.core.repository.IPostRepository;
import org.example.presentation.dto.PostDTO;
import org.example.util.PostMapper;

public class RetrieveFeedCase {
    private final IPostRepository postRepository;

    public RetrieveFeedCase(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<PostDTO> execute(User user, String mode) {
        List<Post> posts = "abonnements".equals(mode)
            ? postRepository.getPostsFrom(user)
            : postRepository.getPosts();

        return posts.stream()
            .map(PostMapper::toDTO)
            .toList();
    }
}