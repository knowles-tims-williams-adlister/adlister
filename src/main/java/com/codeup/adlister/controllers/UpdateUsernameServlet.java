package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-username")
public class UpdateUsernameServlet extends HttpServlet {

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
        // if not user is logged in redirect to login page
        if(req.getSession().getAttribute("user") == null) {
            req.getSession().setAttribute("redirect", "/update-username");
            resp.sendRedirect("/login");
            // add a return statement to exit out of the entire method.
            return;
        }

        req.getRequestDispatcher("/WEB-INF/update-profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User loggedInUser = (User) req.getSession().getAttribute("user"); //grab the current session user.
        String username = req.getParameter("username");

        boolean inputHasErrors = username.isEmpty(); //ensure that the username input field is not left blank

        //redirect if username input field is left blank
        if(inputHasErrors){
            String message = "Username was left empty. Could not update!";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/WEB-INF/update-profile.jsp").forward(req,resp);
            return;
        }

        DaoFactory.getUsersDao().updateUsername(loggedInUser, username);// updated username in DB

        req.getSession().setAttribute("user", DaoFactory.getUsersDao().findByUsername(username));// updated session user
        resp.sendRedirect("/profile");

    }
}
