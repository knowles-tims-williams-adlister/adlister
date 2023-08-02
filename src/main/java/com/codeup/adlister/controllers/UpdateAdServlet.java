package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;

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

        if (adToUpdate != null) {
            request.setAttribute("ad", adToUpdate);
            request.getRequestDispatcher("/WEB-INF/ads/update.jsp").forward(request, response);
        } else {
            response.sendRedirect("/");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            //Retrieve form parameters from the request
            Long adId = Long.parseLong(request.getParameter("adId"));
            String newTitle = request.getParameter("updateTitle");
            String newDescription = request.getParameter("updateDescription");

            // Fetch the existing ad from the database using adId
            Ad adToUpdate = DaoFactory.getAdsDao().getById(adId);

            // Check if the ad exists
            if (adToUpdate != null) {
                // // Update the ad with the new values
                adToUpdate.setTitle(newTitle);
                adToUpdate.setDescription(newDescription);

                // Save the updated ad to the database
                DaoFactory.getAdsDao().update(adToUpdate);

                // Redirect the user to the ad's detail page
                response.sendRedirect("/");
            } else {
                // If the ad does not exist, redirect the user to an error page
                response.sendRedirect("/");
            }
        } catch (Exception e) {
            // Handle any exception here (e.g., log the error or display an error message)
            e.printStackTrace();
            response.sendRedirect("/");

        }

    }
}
