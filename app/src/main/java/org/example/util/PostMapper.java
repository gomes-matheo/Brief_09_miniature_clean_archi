package org.example.util;

import org.example.presentation.dto.PostDTO;
import org.example.core.entities.Post;

public class PostMapper {
    public static PostDTO toDTO(Post post) {
        return new PostDTO(
            post.getId(),
            post.getOwner().getUsername(),
            post.getContent(),
            post.getLikeCount(),
            post.getCreatedAt().toString()
        );
    }
}
