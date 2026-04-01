package org.example.controller;

import java.io.IOException;

import org.example.core.entities.User;
import org.example.presentation.dto.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.infrastructure.config.ServiceLocator;

@WebServlet("/post")
public class PostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
        ServiceLocator sl = ServiceLocator.getInstance();

        User loggedUser = (User) req.getSession().getAttribute("loggedUser");

        UserDTO userView = new UserDTO(loggedUser.getUsername(), loggedUser.getEmail());

        req.setAttribute("user", userView);


        String content = req.getParameter("content");
        try {
            sl.getCreatePostCase().sendPost(loggedUser, content);
            resp.sendRedirect(req.getContextPath() + "/feed");
        } catch (IllegalArgumentException e) {
            resp.sendError(403, "Post cannot be empty");
            // resp.sendRedirect(req.getContextPath() + "/feed?error=empty");
        } catch (Exception e) {
            resp.sendError(404, "Something bad happened");
        }
    }
}