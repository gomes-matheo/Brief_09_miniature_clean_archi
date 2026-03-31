package org.example.infrastructure.persistence;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LikeRepository {
    private Set<String> likes = Collections.synchronizedSet(new HashSet<>());
    private static final LikeRepository LIKE_REPO_INSTANCE = new LikeRepository();

    private LikeRepository() {}

    public static LikeRepository getInstance() {
        return LIKE_REPO_INSTANCE;
    }

    public void addLike() {
        likes.add(null);
    }

    public void removeLike() {
        likes.remove(null);
    }

    public Set<String> getLikes() {
        return likes;
    }
}