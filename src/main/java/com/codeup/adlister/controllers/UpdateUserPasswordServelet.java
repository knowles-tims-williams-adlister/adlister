package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-password")
public class UpdateUserPasswordServelet extends HttpServlet {

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
        // if not user in session redirect to login page
        if(req.getSession().getAttribute("user") == null) {
            req.getSession().setAttribute("redirect", "/update-password");
            resp.sendRedirect("/login");
            // add a return statement to exit out of the entire method.
            return;
        }

        req.getRequestDispatcher("/WEB-INF/update-profile.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // get the current user in the session along with each password input field
        // old, new, and confirm fields.
        User loggedinUser = (User) req.getSession().getAttribute("user");
        String oldPassword = req.getParameter("old_password");
        String newPassword = req.getParameter("new_password");
        String confirmPassword = req.getParameter("confirm_password");

        /**
         * validate input
         * no field should be left blank
         * new and confirm fields should match.
         * old and new fields should not match.
         */
        boolean inputHasErrors = oldPassword.isEmpty()
                || newPassword.isEmpty()
                || confirmPassword.isEmpty()
                || (! newPassword.equals(confirmPassword))
                || (oldPassword.equals(newPassword));

        // if not validated send back to update form page.
        if(inputHasErrors){
            String message = "Could not update password!";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/WEB-INF/update-profile.jsp").forward(req,resp);
            return;
        }

        boolean validAttempt = Password.check(oldPassword, loggedinUser.getPassword()); // check that the old password matches the current password on file before doing anything.

        /**
         * if old password matches,
         * perform has on new password and update it in DB for the user.
         *
         * log the user out after updating the password.
         *
         * if old does not match
         * send user back to update forms page
         */
        if(validAttempt){
            String hash = Password.hash(newPassword);

            DaoFactory.getUsersDao().updatePassword(loggedinUser, hash);

            resp.sendRedirect("/logout");
        } else {
            String message = "Old Password does not match! Please try again";
            req.setAttribute("message", message);
            req.getRequestDispatcher("/WEB-INF/update-profile.jsp").forward(req,resp);
        }
    }
}
