package org.example.controller;

import java.io.IOException;
import java.util.List;

import org.example.application.usecases.RetrieveFeedCase;
import org.example.core.entities.Post;
import org.example.core.entities.User;
import org.example.infrastructure.persistence.PostRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/feed")
public class FeedServlet extends HttpServlet {
    private final RetrieveFeedCase getFeedUseCase = new RetrieveFeedCase(PostRepository.getInstance());
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

        List<Post> posts = getFeedUseCase.execute(loggedUser.getId(), mode);
      

        req.setAttribute("posts", posts);
        req.setAttribute("mode", mode);
        req.setAttribute("loggedUser", loggedUser);
        req.getRequestDispatcher("/WEB-INF/views/feed.jsp").forward(req, resp);
    }
}