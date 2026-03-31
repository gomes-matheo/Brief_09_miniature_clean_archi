package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.core.entities.User;
import org.example.core.usecase.CreatePostUseCase;
import org.example.util.A_TRIER_DataStore;

import java.io.IOException;

@WebServlet("/post")
public class PostServlet extends HttpServlet {

    private final CreatePostUseCase createPostUseCase = new CreatePostUseCase(DataStorePostRepository.getInstance());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User loggedUser = (User) req.getSession().getAttribute("loggedUser");
        if (loggedUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String content = req.getParameter("content");
        try {
            createPostUseCase.execute(loggedUser, content);
            resp.sendRedirect(req.getContextPath() + "/feed");
        } catch (IllegalArgumentException e) {
            resp.sendRedirect(req.getContextPath() + "/feed?error=empty");

        }
    }
}