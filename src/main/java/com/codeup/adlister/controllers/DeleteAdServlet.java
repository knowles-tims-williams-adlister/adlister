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

@WebServlet(name = "DeleteAdServlet", urlPatterns = "/deleteAd")
public class DeleteAdServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long adId = Long.parseLong(request.getParameter("id"));
        Ad adToDelete = DaoFactory.getAdsDao().getById(adId);
        User user = (User) request.getSession().getAttribute("user");
        if (user.getId() == adToDelete.getUserId()) {
            request.setAttribute("ad", adToDelete);

            request.getRequestDispatcher("/WEB-INF/ads/delete.jsp").forward(request, response);
        } else {
            response.sendRedirect("/profile");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String input = request.getParameter("delete"); //get the input value for delete.
        long adId = Long.parseLong(request.getParameter("id"));

        /**
         * if user inputs Delete into the input field, delete the user from the DB
         * and log them out. else, create and send a message back to the page
         * displaying a message about not deleting the user.
         */
        if(input.equals("Delete")) {
            System.out.println("adId = " + adId);
            DaoFactory.getAdsDao().delete(adId);

            response.sendRedirect("/ads");
        } else {
            String message = "Could not delete ad! (Delete) was not entered!";
            request.setAttribute("message", message);
            request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request,response);
        }
    }
}
