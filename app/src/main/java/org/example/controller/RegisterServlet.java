package org.example.controller;

import java.io.IOException;

import org.example.core.exceptions.EntityAlreadyExistsException;
import org.example.infrastructure.config.ServiceLocator;

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

        ServiceLocator sl = ServiceLocator.getInstance();

        try {
            sl.getCreateUserCase().createUserSuccess(username, email, password);
        } catch (IllegalArgumentException e) {
            resp.sendError(403, "Form was empty");
            return;
        } catch (EntityAlreadyExistsException e) {
            resp.sendError(403, "User already exists");
            return;
        } catch (Exception e) {
            resp.sendError(405, "Something wrong happened");
            return;
        } 
        
        resp.sendRedirect(req.getContextPath() + "/login?success=registered");
    }
}