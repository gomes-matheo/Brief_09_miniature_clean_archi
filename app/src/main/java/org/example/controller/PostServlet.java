package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.example.Core.entities.User;
import org.example.util.DataStore;

import java.io.IOException;

@WebServlet("/post")
public class PostServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User loggedUser = (User) req.getSession().getAttribute("loggedUser");
        if (loggedUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String content = req.getParameter("content");
        if (content == null || content.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/feed?error=empty");
            return;
        }

        DataStore.getInstance().createPost(loggedUser, content);
        resp.sendRedirect(req.getContextPath() + "/feed");
    }
}