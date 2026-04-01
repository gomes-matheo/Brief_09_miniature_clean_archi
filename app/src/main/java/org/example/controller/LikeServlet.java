package org.example.controller;

import java.io.IOException;

import org.example.application.usecases.ToggleLikeCase;
import org.example.core.entities.User;
import org.example.infrastructure.persistence.LikeRepository;
import org.example.presentation.dto.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {

    private final ToggleLikeCase toggleLikeUseCase = new ToggleLikeCase(LikeRepository.getInstance());
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
        toggleLikeUseCase.execute(loggedUser.getId(), postId);

        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer != null ? referer : req.getContextPath() + "/feed");
    }
}