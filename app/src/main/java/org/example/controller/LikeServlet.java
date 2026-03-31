package org.example.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

<<<<<<< HEAD
import org.example.Core.entities.User;
=======
import org.example.Core.usecase.ToggleLikeUseCase;
import org.example.model.User;
>>>>>>> 3c9dbef14bf8737ff8be19c977232a7ca36fa1ff
import org.example.util.DataStore;

import java.io.IOException;

@WebServlet("/like")
public class LikeServlet extends HttpServlet {

    private final ToggleLikeUseCase toggleLikeUseCase;
    public LikeServlet() {
        this.toggleLikeUseCase = new ToggleLikeUseCase((org.example.Core.repository.IntLikeRepository) DataStore.getInstance());
    }
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
        toggleLikeUseCase.execute(loggedUser.getId(), postId);

        String referer = req.getHeader("Referer");
        resp.sendRedirect(referer != null ? referer : req.getContextPath() + "/feed");
    }
}