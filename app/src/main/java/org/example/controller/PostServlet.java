package org.example.controller;

import java.io.IOException;

import org.example.application.usecases.CreatePostCase;
import org.example.core.entities.User;
import org.example.infrastructure.persistence.PostRepository;
import org.example.presentation.dto.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/post")
public class PostServlet extends HttpServlet {

    private final CreatePostCase createPostUseCase = new CreatePostCase(PostRepository.getInstance());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User loggedUser = (User) req.getSession().getAttribute("loggedUser");

        UserDTO userView = new UserDTO(loggedUser.getUsername(), loggedUser.getEmail());

        req.setAttribute("user", userView);


        String content = req.getParameter("content");
        try {
            createPostUseCase.execute(loggedUser, content);
            resp.sendRedirect(req.getContextPath() + "/feed");
        } catch (IllegalArgumentException e) {
            resp.sendRedirect(req.getContextPath() + "/feed?error=empty");

        }
    }
}