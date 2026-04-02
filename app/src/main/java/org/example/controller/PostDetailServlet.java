package org.example.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.example.core.entities.Comment;
import org.example.core.entities.Post;
import org.example.core.entities.User;
import org.example.core.repository.ICommentRepository;
import org.example.core.repository.IFollowerRepository;
import org.example.core.repository.ILikeRepository;
import org.example.core.repository.IPostRepository;
import org.example.infrastructure.config.ServiceLocator;
import org.example.infrastructure.persistence.PostRepository;
import org.example.presentation.dto.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/post-detail")
public class PostDetailServlet extends HttpServlet {
    
    private final ServiceLocator sl = ServiceLocator.getInstance();
    private ILikeRepository likeRepo;
    private IPostRepository postRepo;
    private ICommentRepository commentRepo;

    private final String POST_DETAIL_PATH = "/WEB-INF/views/post_detail.jsp";

    private final String LOGGED_USER = "loggedUser";
    private final String USER = "userView";
    private final String POST_ID = "postId";
    private final String POST_SECTION = "post";
    private final String COMMENTS_SETION = "comments";
    private final String COMMENT_CONTENT = "content";

    private Post getPostById(long postId) {
        Optional<Post> maybePost = postRepo.getPostById(postId);
        if(maybePost.isEmpty()) {
            // req.getRequestDispatcher("/WEB-INF/views/post_detail.jsp").forward(req, resp);
            return null;
        } else {
            return maybePost.get();
        }
    }

    private long isOkayId (String id) throws NumberFormatException {
        long postId = Long.parseLong(id);
        return postId;
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        final User loggedUser = (User) req.getSession().getAttribute(LOGGED_USER);
        final UserDTO userView = new UserDTO(loggedUser.getUsername(), loggedUser.getEmail());

        req.setAttribute(USER, userView);

        // POST ID VERIFICATION
        long postId;
        try {
            postId = isOkayId(req.getParameter(POST_ID));
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/feed");
            return;
        }

        // POST VERIFICATION
        Post post = getPostById(postId);
        if (post  == null) {
            resp.sendError(404, "post not found");
            return;
        }

        // COMMENTS RETRIEVING
        post.setLikeCount(likeRepo.getLikes(post));
        List<Comment> comments = commentRepo.getCommentsFromPost(post);

        req.setAttribute(POST_SECTION, post);
        req.setAttribute(COMMENTS_SETION, comments);
        req.setAttribute(LOGGED_USER, loggedUser);
        req.getRequestDispatcher(POST_DETAIL_PATH).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        // LOGIN VERIFICATION
        User loggedUser = (User) req.getSession().getAttribute(LOGGED_USER);
        if (loggedUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // POST ID VERIFICATION
        long postId;
        try {
            postId = isOkayId(req.getParameter(POST_ID));
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/feed");
            return;
        }

        // POST VERIFICATION
        Post post = getPostById(postId);
        if (post  == null) {
            resp.sendError(404, "post not found");
            return;
        }

        // CONTENT VERIFICATION
        String content = req.getParameter(COMMENT_CONTENT);
        if (content == null) {
            content = req.getParameter(COMMENTS_SETION);
        }
        
        sl.CreateCommentCase().sendComment(post, loggedUser, content);
        resp.sendRedirect(req.getContextPath() + "/feed");
    }
}