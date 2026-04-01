package org.example.controller;

import java.io.IOException;

import org.example.core.entities.User;

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
        
        if (loggedUser == null) {
            // resp.sendError(403, "User must be logged in");
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

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