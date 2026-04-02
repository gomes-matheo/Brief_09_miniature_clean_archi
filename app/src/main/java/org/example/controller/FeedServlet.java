package org.example.controller;

import java.io.IOException;
import java.util.List;
import org.example.core.entities.User;
import org.example.presentation.dto.PostDTO;
import org.example.infrastructure.config.ServiceLocator;
import org.example.presentation.dto.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/feed")
public class FeedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        final User loggedUser = (User) req.getSession().getAttribute("loggedUser");
        final UserDTO userView = new UserDTO(loggedUser.getUsername(), loggedUser.getEmail());
        final ServiceLocator sl = ServiceLocator.getInstance();

        req.setAttribute("user", userView);

        String mode = req.getParameter("mode");
        if (mode == null)
            mode = "reco";

        List<PostDTO> posts = sl.getRetrieveFeedCase().execute(loggedUser, mode);

        req.setAttribute("posts", posts);
        req.setAttribute("mode", mode);
        req.setAttribute("loggedUser", loggedUser);
        req.getRequestDispatcher("/WEB-INF/views/feed.jsp").forward(req, resp);
    }
}