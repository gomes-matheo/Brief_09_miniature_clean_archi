package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.model.User;
import org.example.util.DataStore;

import java.io.IOException;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User loggedUser = (User) req.getSession().getAttribute("loggedUser");
        if (loggedUser == null) {
            resp.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        String postIdParam = req.getParameter("postId");
        if (postIdParam == null) {
            resp.sendRedirect(req.getContextPath() + "/feed");
            return;
        }

        long postId = Long.parseLong(postIdParam);
        DataStore.getInstance().toggleLike(loggedUser.getId(), postId);

        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer != null ? referer : req.getContextPath() + "/feed");
    }
}