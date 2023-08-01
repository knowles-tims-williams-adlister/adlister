package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-email")
public class UpdateUserEmailServlet extends HttpServlet {

    /**
     * Send the user to the update-profile form page if they are logged in,
     * else send them to the login page.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // if no user is logged into session redirect to login page
        if(req.getSession().getAttribute("user") == null) {
            req.getSession().setAttribute("redirect", "/update-email");
            resp.sendRedirect("/login");
            // add a return statement to exit out of the entire method.
            return;
        }

        req.getRequestDispatcher("/WEB-INF/update-profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedinUser = (User) req.getSession().getAttribute("user"); //get current session user.
        String email = req.getParameter("email"); //get inputed email

        boolean inputHasErrors = email.isEmpty(); // ensure the email input field is not left blank

        // if it is left blank send back to page for updating profile info.
        if(inputHasErrors){
            resp.sendRedirect("/update-email");
            return;
        }

        DaoFactory.getUsersDao().updateEmail(loggedinUser, email); // update user email in DB

        req.getSession().setAttribute("user", DaoFactory.getUsersDao().findByUsername(loggedinUser.getUsername())); // retrieve new user info from DB and set it to the current session user.
        resp.sendRedirect("/profile");
    }
}
