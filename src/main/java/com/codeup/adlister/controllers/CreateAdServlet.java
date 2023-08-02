package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.Ad;
import com.codeup.adlister.models.Category;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(name = "controllers.CreateAdServlet", urlPatterns = "/ads/create")
public class CreateAdServlet extends HttpServlet {
    private String message;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("user") == null) {
            request.getSession().setAttribute("redirect", "/ads/create");
            response.sendRedirect("/login");
            return;
        }
//        List<Category> categoryList =  DaoFactory.getCategoriesDAO().all();
//        for (Category c :
//                categoryList) {
//            System.out.println(c.getName());
//        }
        request.setAttribute("allCategories", DaoFactory.getCategoriesDAO().all());

        request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        User loggedInUser = (User) request.getSession().getAttribute("user");
        String title = request.getParameter("title");
        String description = request.getParameter("description");

        // If validation fails, store the input values in the request and forward back to the form page.

        if (!isValid(title, description)) {
            request.setAttribute("title", title); // Set the title as an attribute in the request
            request.setAttribute("description", description); // Set the description as an attribute in the request

            request.setAttribute("message", message);

            request.getRequestDispatcher("/WEB-INF/ads/create.jsp").forward(request, response);
            return;
        }
       Ad ad = new Ad(loggedInUser.getId(), title, description);
        long adId = DaoFactory.getAdsDao().insert(ad); // Get the inserted ad's ID

        // Associate the selected categories with the ad
        String[] selectedCategories = request.getParameterValues("categories");
        System.out.println(Arrays.toString(selectedCategories));
        if (selectedCategories != null) {
            for (String categoryIdStr : selectedCategories) {
                long categoryId = Long.parseLong(categoryIdStr);
                System.out.println(categoryId);
                DaoFactory.getAdsDao().insertAdCategory(adId, categoryId);
            }
        }
        // Validation passed create and insert the ad as before
        response.sendRedirect("/ads");
    }
    private boolean isValid(String title, String description) {
        if (title == null || title.trim().isEmpty()) {
            message = "Title was left empty. Please fill it out.";
            return false;
        }

        if (description == null || description.trim().isEmpty()) {
            message = "Description left empty. Please fill it out.";
            return false;
        }

        int maxTitleLength = 100;
        int maxDescriptionLength = 500;

        if (title.length() > maxTitleLength) {
            message = "Title is to long, please shorten it.";
            return false;
        }

        if (description.length() > maxDescriptionLength) {
            message = "Description is to long, please shorten it.";
            return false;
        }

        return true; // Return true if the input is valid; false otherwise
    }
}