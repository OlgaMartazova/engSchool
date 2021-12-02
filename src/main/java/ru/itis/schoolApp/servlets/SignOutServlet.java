package ru.itis.schoolApp.servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/sign-out")
public class SignOutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Cookie cookie = new Cookie("token", "");
        cookie.setMaxAge(1);
        response.addCookie(cookie);
        request.getSession(true).removeAttribute("user");
        response.sendRedirect("/sign-in");
    }
}
