package org.example.presentation.dto;

public record PostDTO(
        long id,
        String owner,
        String content,
        int likeCount,
        String formattedDate) {
}
