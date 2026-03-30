package org.example.controller;

import java.io.IOException;

import org.example.util.DataStore;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String username = req.getParameter("username");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (username == null || username.isBlank() ||
                email == null || email.isBlank() ||
                password == null || password.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/register?error=missing");
            return;
        }

        DataStore store = DataStore.getInstance();
        if (store.existsUserByUsernameOrEmail(username, email)) {
            resp.sendRedirect(req.getContextPath() + "/register?error=exists");
            return;
        }

        store.createUser(username, email, password);
        resp.sendRedirect(req.getContextPath() + "/login?success=registered");
    }
}