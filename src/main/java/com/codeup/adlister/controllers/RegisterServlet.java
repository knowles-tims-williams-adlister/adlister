package com.codeup.adlister.controllers;

import com.codeup.adlister.dao.DaoFactory;
import com.codeup.adlister.models.User;
import com.codeup.adlister.util.Password;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "controllers.RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String passwordConfirmation = request.getParameter("confirm_password");

        // Validate input
        boolean inputHasErrors = false;

        // Checks if username is empty or already exists
        if (username.isEmpty() || DaoFactory.getUsersDao().findByUsername(username) != null) {
            inputHasErrors = true;
            request.setAttribute("usernameError", "Invalid username or username already taken.");
        }

        // Checks if email is empty or already exists
        if (email.isEmpty() || DaoFactory.getUsersDao().findByEmail(email) != null) {
            inputHasErrors = true;
            request.setAttribute("emailError", "Invalid email or email already registered.");
        }

        // Checks if password is empty
        if (password.isEmpty()) {
            inputHasErrors = true;
            request.setAttribute("passwordError", "Password cannot be empty.");
        }

        // Checks if password confirmation matches password
        if (!password.equals(passwordConfirmation)) {
            inputHasErrors = true;
            request.setAttribute("passwordConfirmError", "Password confirmation does not match.");
        }

        if (inputHasErrors) {
            // Sets the user's input as attributes in the request
            request.setAttribute("username", username);
            request.setAttribute("email", email);
            request.setAttribute("password", password);
            request.setAttribute("confirm_password", "");

            request.getRequestDispatcher("/WEB-INF/register.jsp").forward(request, response);
            return;
        }

        // creates and save a new user
        User user = new User(username, email, password);

        // hashing the password
        String hash = Password.hash(user.getPassword());

        user.setPassword(hash);

        DaoFactory.getUsersDao().insert(user);
        response.sendRedirect("/login");
    }
}
