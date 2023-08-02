package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateAdServlet", urlPatterns = "/ads/updateAd")
public class UpdateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long adId = Long.parseLong(request.getParameter("id"));
        Ad adToUpdate = DaoFactory.getAdsDao().getById(adId);
        User user = (User) request.getSession().getAttribute("user");
        if (user.getId() == adToUpdate.getUserId()) {
            request.setAttribute("ad", adToUpdate);
            request.getRequestDispatcher("/WEB-INF/ads/update.jsp").forward(request, response);
        } else {
            response.sendRedirect("/profile");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long adId = Long.parseLong(request.getParameter("id"));
        User user = (User) request.getSession().getAttribute("user");
        String newTitle = request.getParameter("title");
        String newDescription = request.getParameter("description");
        if (request.getParameter("updateButton") != null) {
            if (newTitle != null && newDescription != null && !newTitle.isEmpty() && !newDescription.isEmpty()) {
                Ad editAd = new Ad(
                        adId,
                        user.getId(),
                        newTitle,
                        newDescription
                );
                DaoFactory.getAdsDao().update(editAd);
            } else {
                String message = "Could not update ad!";
                request.setAttribute("message", message);
                request.setAttribute("newTitle", newTitle);
                request.setAttribute("newDescription", newDescription);
                request.setAttribute("ad", DaoFactory.getAdsDao().getById(adId));

                request.getRequestDispatcher("/WEB-INF/ads/update.jsp").forward(request, response);
            }
            response.sendRedirect("/profile");
        } else {
            response.sendRedirect("/profile");
        }
    }
}
