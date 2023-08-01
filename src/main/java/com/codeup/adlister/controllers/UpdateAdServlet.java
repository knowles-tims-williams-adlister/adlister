package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UpdateAdServlet", urlPatterns = "/updateAd")
public class UpdateAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long adId = Long.parseLong(request.getParameter("id"));
        Ad adToUpdate = DaoFactory.getAdsDao().getById(adId);

        if (adToUpdate != null) {
            request.setAttribute("ad", adToUpdate);
            request.getRequestDispatcher("/WEB-INF/ads/update.jsp").forward(request, response);
        } else {
            response.sendRedirect("/error-page");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long adId = Long.parseLong(request.getParameter("adId"));
        String newTitle = request.getParameter("title");
        String newDescription = request.getParameter("description");

        Ad adToUpdate = DaoFactory.getAdsDao().getById(adId);
        if (adToUpdate != null) {
            // Sets the ad's current values as attributes in the request
            request.setAttribute("ad", adToUpdate);

            // Sets the form input values as attributes in the request
            request.setAttribute("newTitle", newTitle);
            request.setAttribute("newDescription", newDescription);

            request.getRequestDispatcher("/WEB-INF/ads/update.jsp").forward(request, response);
        } else {
            response.sendRedirect("/error-page");
        }
    }
}
