package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.Core.usecase.GetFeedUseCase;
import org.example.model.Post;
import org.example.model.User;
import org.example.util.DataStore;

import java.io.IOException;
import java.util.List;

@WebServlet("/feed")
public class FeedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User loggedUser = (User) req.getSession().getAttribute("loggedUser");
        if (loggedUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String mode = req.getParameter("mode");
        if (mode == null)
            mode = "reco";

        DataStore store = DataStore.getInstance();
        List<Post> posts;
        if ("abonnements".equals(mode)) {
            posts = store.getPostsFromFollowed(loggedUser.getId());
        } else {
            posts = store.getAllPosts();
        }
        posts.forEach(p -> p.setLikeCount(store.likeCount(p.getId())));

        req.setAttribute("posts", posts);
        req.setAttribute("mode", mode);
        req.setAttribute("loggedUser", loggedUser);
        req.getRequestDispatcher("/WEB-INF/views/feed.jsp").forward(req, resp);
    }
}