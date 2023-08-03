package com.codeup.adlister.controllers;
import com.codeup.adlister.models.Ad;

import com.codeup.adlister.dao.DaoFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchAdServlet", urlPatterns = "/search")
public class SearchAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Display the search form (ad_search.jsp) when a GET request is made to /search
        request.getRequestDispatcher("WEB-INF/ads/search.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");

        List<Ad> searchByTitle;

        if (keyword != null && !keyword.isEmpty()) {
            searchByTitle = DaoFactory.getAdsDao().searchByTitle(keyword);
        } else {
            searchByTitle = DaoFactory.getAdsDao().all();
        }
        request.setAttribute("ads", searchByTitle);
        request.getRequestDispatcher("/WEB-INF/ads/index.jsp").forward(request, response);
    }
}
