package org.example.controller;

import java.io.IOException;

import org.example.core.entities.User;
import org.example.core.repository.IPostRepository;
import org.example.infrastructure.config.ServiceLocator;
import org.example.presentation.dto.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {

    private final ServiceLocator sl = ServiceLocator.getInstance();
    IPostRepository postRepo;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User loggedUser = (User) req.getSession().getAttribute("loggedUser");

        UserDTO userView = new UserDTO(loggedUser.getUsername(), loggedUser.getEmail());

        req.setAttribute("user", userView);


        String postIdParam = req.getParameter("postId");
        if (postIdParam == null) {
            resp.sendRedirect(req.getContextPath() + "/feed");
            return;
        }

        long postId = Long.parseLong(postIdParam);
        postRepo.getPostById(postId).ifPresent(post -> {
                sl.getToggleLikeCase().toggleLike(post, loggedUser.getId());
            }
        );

        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer != null ? referer : req.getContextPath() + "/feed");
    }
}