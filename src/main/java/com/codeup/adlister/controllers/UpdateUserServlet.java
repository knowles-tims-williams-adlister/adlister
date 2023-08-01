package com.codeup.adlister.controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/update-profile")
public class UpdateUserServlet extends HttpServlet {

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
        //if not user is logged in redirect to login page.
        if(req.getSession().getAttribute("user") == null) {
            req.getSession().setAttribute("redirect", "/update-profile");
            resp.sendRedirect("/login");
            // add a return statement to exit out of the entire method.
            return;
        }

        req.getRequestDispatcher("/WEB-INF/update-profile.jsp").forward(req, resp);
    }
}
