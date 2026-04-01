package org.example.util;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.example.core.entities.Comment;
import org.example.core.entities.Post;
import org.example.core.entities.User;

public class A_TRIER_DataStore {

    

    public static A_TRIER_DataStore getInstance() {
        return INSTANCE;
    }

    // public User createUser(String username, String email, String password) {
    //     User u = new User();
    //     u.setId(userIdSeq.getAndIncrement());
    //     u.setUsername(username);
    //     u.setEmail(email);
    //     u.setPassword(password);
    //     u.setCreatedAt(LocalDateTime.now());
    //     users.add(u);
    //     return u;
    // }



    // public Post createPost(User owner, String content) {
    //     Post p = new Post();
    //     p.setId(postIdSeq.getAndIncrement());
    //     p.setOwner(owner);
    //     p.setContent(content);
    //     p.setCreatedAt(LocalDateTime.now());
    //     posts.add(p);
    //     return p;
    // }

    public int likeCount(long postId) {
        return (int) likes.stream().filter(l -> l.endsWith(":" + postId)).count();
    }

    // public Comment createComment(long postId, User author, String content) {
    //     Comment c = new Comment();
    //     c.setId(commentIdSeq.getAndIncrement());
    //     c.setPostId(postId);
    //     c.setOwner(author);
    //     c.setContent(content);
    //     c.setCreatedAt(LocalDateTime.now());
    //     comments.add(c);
    //     return c;
    // }
}
