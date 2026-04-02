package org.example.controller;

import java.io.IOException;
import java.util.Optional;

import org.example.core.entities.User;
import org.example.infrastructure.config.ServiceLocator;
import org.example.presentation.dto.UserDTO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        User loggedUser = (User) req.getSession().getAttribute("loggedUser");
        UserDTO userView = new UserDTO(loggedUser.getUsername(), loggedUser.getEmail());

        ServiceLocator sl = ServiceLocator.getInstance();

        req.setAttribute("user", userView);

        Optional<User> found = sl.getAuthenticateUserCase().execute(username, password);
        if (found.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/login?error=invalid");
            return;
        }

        HttpSession session = req.getSession();
        session.setAttribute("loggedUser", found.get());
        resp.sendRedirect(req.getContextPath() + "/feed");
    }
}
