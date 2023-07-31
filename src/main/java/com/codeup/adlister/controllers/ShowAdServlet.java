package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.Ads;
import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.dao.Users;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.ShowAdServlet", urlPatterns = "/ads/show")
public class ShowAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the ad ID from the request parameter
        long adId = Long.parseLong(request.getParameter("id"));

        // Assuming you have methods to fetch the ad and user data based on the ID
        Ad ad = (Ad) DaoFactory.getAdsDao().getById(adId);
        User user = (User) request.getSession().getAttribute("user");

        // Pass the ad and user objects as attributes to the JSP
        request.setAttribute("ad", ad);
        request.setAttribute("user", user);

        // Forward the request to the showAd.jsp page for rendering
        request.getRequestDispatcher("/WEB-INF/ads/ad_info.jsp").forward(request, response);
    }
}
