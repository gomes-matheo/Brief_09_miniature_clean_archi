package org.example.infrastructure.persistence;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LikeRepository {
    private Set<String> likes = Collections.synchronizedSet(new HashSet<>());

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