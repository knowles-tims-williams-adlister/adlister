package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/delete-user")
public class DeleteUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //if not user is logged in redirect to login page.
        if(req.getSession().getAttribute("user") == null) {
            resp.sendRedirect("/login");
            // add a return statement to exit out of the entire method.
            return;
        }

        req.getRequestDispatcher("/WEB-INF/update-profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String input = req.getParameter("delete"); //get the input value for delete.

        /**
         * if user inputed Delete into the input field, delete the user from the DB
         * and log them out. else, create and send a message back to the page
         * displaying a message about not deleting the user.
         */
        if(input.equals("Delete")) {
            DaoFactory.getUsersDao().deleteUser((User) req.getSession().getAttribute("user"));
            resp.sendRedirect("/logout");
        } else {
            String message = "Could not delete account! (Delete) was not entered!";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/WEB-INF/update-profile.jsp").forward(req,resp);
        }
    }
}
