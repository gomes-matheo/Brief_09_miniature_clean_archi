package org.example.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.example.core.entities.Comment;
import org.example.core.entities.Post;
import org.example.core.entities.User;
import org.example.presentation.dto.UserDTO;
import org.example.util.A_TRIER_DataStore;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/post-detail")
public class PostDetailServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

       User loggedUser = (User) req.getSession().getAttribute("loggedUser");

        UserDTO userView = new UserDTO(loggedUser.getUsername(), loggedUser.getEmail());

        req.setAttribute("user", userView);


        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/feed");
            return;
        }

        long postId;
        try {
            postId = Long.parseLong(idParam);
        } catch (NumberFormatException e) {
            resp.sendRedirect(req.getContextPath() + "/feed");
            return;
        }

        A_TRIER_DataStore store = A_TRIER_DataStore.getInstance();
        Optional<Post> postOpt = store.findPostById(postId);
        if (postOpt.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/feed");
            return;
        }

        Post post = postOpt.get();
        post.setLikeCount(store.likeCount(post.getId()));
        List<Comment> comments = store.getCommentsForPost(postId);

        req.setAttribute("post", post);
        req.setAttribute("comments", comments);
        req.setAttribute("loggedUser", loggedUser);
        req.getRequestDispatcher("/WEB-INF/views/post_detail.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User loggedUser = (User) req.getSession().getAttribute("loggedUser");
        if (loggedUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String idParam = req.getParameter("postId");
        String content = req.getParameter("content");
        if (content == null) {
            content = req.getParameter("comment");
        }
        if (idParam == null || content == null || content.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/feed");
            return;
        }

        long postId = Long.parseLong(idParam);
        A_TRIER_DataStore.getInstance().createComment(postId, loggedUser, content);
        resp.sendRedirect(req.getContextPath() + "/feed");
    }
}