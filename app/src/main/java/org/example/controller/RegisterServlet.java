package org.example.controller;

import java.io.IOException;

import org.example.application.usecases.CreateUserCase;
import org.example.infrastructure.persistence.UserRepository;

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

        if (username == null || email == null || password == null ||
                username.isBlank() || email.isBlank() || password.isBlank()) {
            resp.sendRedirect(req.getContextPath() + "/register?error=missing");
            return;
        }

        CreateUserCase createUserCase = new CreateUserCase(UserRepository.getInstance());
        try {
            createUserCase.createUserSuccess(username, email, password);
            resp.sendRedirect(req.getContextPath() + "/login?success=registered");
        } catch (Exception e) {
            resp.sendRedirect(req.getContextPath() + "/register?error=exists");
        }
    }
}